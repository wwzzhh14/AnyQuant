package MyDemo.wjy.demo;

/**
 * Created by Jiayiwu on 16/3/12.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat;
import org.knowm.xchart.internal.Series_AxesChart;
import org.knowm.xchart.internal.chartpart.Chart;

public class XChartPanel<T extends Chart> extends JPanel {
    private final T chart;
    private final Dimension preferredSize;
    private String saveAsString = "Save As...";
    private static int count=1;
    private int x,x1, y,y1;
    public XChartPanel(T chart,int x,int x1,int y,int y1) {

        this.x=x;
        this.y =y;
        this.x1 = x1;
        this.y1 = y1;
        this.chart = chart;
        this.preferredSize = new Dimension(chart.getWidth(), chart.getHeight());
        this.addMouseListener(new myListener(this));
        this.addMouseMotionListener(new myListener(this));
        KeyStroke ctrlS = KeyStroke.getKeyStroke(83, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        this.getInputMap(2).put(ctrlS, "save");
        this.getActionMap().put("save", new XChartPanel.SaveAction());
    }

    public void setSaveAsString(String saveAsString) {
        this.saveAsString = saveAsString;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        this.chart.paint(g2d, this.getWidth(), this.getHeight());
        g2d.dispose();
    }

    public T getChart() {
        return this.chart;
    }

    public Dimension getPreferredSize() {
        return this.preferredSize;
    }

    private void showSaveAsDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new XChartPanel.SuffixSaveFilter("jpg"));
        XChartPanel.SuffixSaveFilter pngFileFilter = new XChartPanel.SuffixSaveFilter("png");
        fileChooser.addChoosableFileFilter(pngFileFilter);
        fileChooser.addChoosableFileFilter(new XChartPanel.SuffixSaveFilter("bmp"));
        fileChooser.addChoosableFileFilter(new XChartPanel.SuffixSaveFilter("gif"));

        try {
            Class.forName("de.erichseifert.vectorgraphics2d.VectorGraphics2D");
            fileChooser.addChoosableFileFilter(new XChartPanel.SuffixSaveFilter("svg"));
            fileChooser.addChoosableFileFilter(new XChartPanel.SuffixSaveFilter("eps"));
            fileChooser.addChoosableFileFilter(new XChartPanel.SuffixSaveFilter("pdf"));
        } catch (ClassNotFoundException var6) {
            ;
        }

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(pngFileFilter);
        if(fileChooser.showSaveDialog((Component)null) == 0 && fileChooser.getSelectedFile() != null) {
            File theFileToSave = fileChooser.getSelectedFile();

            try {
                if(fileChooser.getFileFilter() == null) {
                    BitmapEncoder.saveBitmap(this.chart, theFileToSave.getCanonicalPath().toString(), BitmapFormat.PNG);
                } else if(fileChooser.getFileFilter().getDescription().equals("*.jpg,*.JPG")) {
                    BitmapEncoder.saveJPGWithQuality(this.chart, BitmapEncoder.addFileExtension(theFileToSave.getCanonicalPath().toString(), BitmapFormat.JPG), 1.0F);
                } else if(fileChooser.getFileFilter().getDescription().equals("*.png,*.PNG")) {
                    BitmapEncoder.saveBitmap(this.chart, theFileToSave.getCanonicalPath().toString(), BitmapFormat.PNG);
                } else if(fileChooser.getFileFilter().getDescription().equals("*.bmp,*.BMP")) {
                    BitmapEncoder.saveBitmap(this.chart, theFileToSave.getCanonicalPath().toString(), BitmapFormat.BMP);
                } else if(fileChooser.getFileFilter().getDescription().equals("*.gif,*.GIF")) {
                    BitmapEncoder.saveBitmap(this.chart, theFileToSave.getCanonicalPath().toString(), BitmapFormat.GIF);
                } else if(fileChooser.getFileFilter().getDescription().equals("*.svg,*.SVG")) {
                    VectorGraphicsEncoder.saveVectorGraphic(this.chart, theFileToSave.getCanonicalPath().toString(), VectorGraphicsFormat.SVG);
                } else if(fileChooser.getFileFilter().getDescription().equals("*.eps,*.EPS")) {
                    VectorGraphicsEncoder.saveVectorGraphic(this.chart, theFileToSave.getCanonicalPath().toString(), VectorGraphicsFormat.EPS);
                } else if(fileChooser.getFileFilter().getDescription().equals("*.pdf,*.PDF")) {
                    VectorGraphicsEncoder.saveVectorGraphic(this.chart, theFileToSave.getCanonicalPath().toString(), VectorGraphicsFormat.PDF);
                }
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

    }

    public Series_AxesChart updateSeries(String seriesName, List<?> newXData, List<? extends Number> newYData, List<? extends Number> newErrorBarData) {
        Map seriesMap = this.chart.getSeriesMap();
        Series_AxesChart series = (Series_AxesChart)seriesMap.get(seriesName);
        if(series == null) {
            throw new IllegalArgumentException("Series name >" + seriesName + "< not found!!!");
        } else {
            if(newXData == null) {
                ArrayList generatedXData = new ArrayList();

                for(int i = 1; i <= newYData.size(); ++i) {
                    generatedXData.add(Integer.valueOf(i));
                }

                series.replaceData(generatedXData, newYData, newErrorBarData);
            } else {
                series.replaceData(newXData, newYData, newErrorBarData);
            }

            this.revalidate();
            this.repaint();
            return series;
        }
    }

    private class XChartPanelPopupMenu extends JPopupMenu {
        JMenuItem saveAsMenuItem;

        public XChartPanelPopupMenu() {
            this.saveAsMenuItem = new JMenuItem(XChartPanel.this.saveAsString);
            this.saveAsMenuItem.addMouseListener(new MouseListener() {
                public void mouseReleased(MouseEvent e) {
                    XChartPanel.this.showSaveAsDialog();
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(this.saveAsMenuItem);
        }
    }



private class myListener extends MouseAdapter{

    JPanel panel;
    public myListener(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

//        Graphics2D gg = (Graphics2D) panel.getGraphics();
//        float lineWidth = 3.0f;
//        gg.setStroke(new BasicStroke(lineWidth));
//        gg.drawLine(e.getX(),y,e.getX(),y1);
//        gg.drawLine(x,e.getY(),x1,e.getY());
//
//        if(count%2==0)
//            repaint();
//        count++;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Graphics2D gg = (Graphics2D) panel.getGraphics();
        float lineWidth = 3.0f;
        gg.setStroke(new BasicStroke(lineWidth));
        gg.drawLine(e.getX(),y1,e.getX(),y);


        gg.drawLine(x1,e.getY(),x,e.getY());

        if(count%5==0)
            repaint();
        count++;

//        Graphics2D gg = (Graphics2D) panel.getGraphics();
//        float lineWidth = 3.0f;
//        gg.setStroke(new BasicStroke(lineWidth));
//        gg.drawLine(e.getX(),y,e.getX(),y1);
//        gg.drawLine(x,e.getY(),x1,e.getY());
//        gg.draw3DRect(e.getX(),e.getY(),x1,y1,true);
//        repaint();
    }
}

    private class PopUpMenuClickListener extends MouseAdapter {
        private PopUpMenuClickListener() {
        }

        public void mousePressed(MouseEvent e) {
            if(e.isPopupTrigger()) {
                this.doPop(e);
            }

        }

        public void mouseReleased(MouseEvent e) {
            if(e.isPopupTrigger()) {
                this.doPop(e);
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            repaint();

        }

        private void doPop(MouseEvent e) {
            System.out.println(e.getX()+"-----------"+e.getY());
            XChartPanel.XChartPanelPopupMenu menu = XChartPanel.this.new XChartPanelPopupMenu();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private class SuffixSaveFilter extends FileFilter {
        private final String suffix;

        public SuffixSaveFilter(String suffix) {
            this.suffix = suffix;
        }

        public boolean accept(File f) {
            if(f.isDirectory()) {
                return true;
            } else {
                String s = f.getName();
                return s.endsWith("." + this.suffix) || s.endsWith("." + this.suffix.toUpperCase());
            }
        }

        public String getDescription() {
            return "*." + this.suffix + ",*." + this.suffix.toUpperCase();
        }
    }

    private class SaveAction extends AbstractAction {
        public SaveAction() {
            super("save");
        }

        public void actionPerformed(ActionEvent e) {
            XChartPanel.this.showSaveAsDialog();
        }
    }
}