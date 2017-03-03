/**
 * Created by HP on 2016/5/23.
 */
jQuery(document).ready(function(){

    if(isFF()){
        jQuery('html').addClass('ff');
    }

    if(!isMobile()) {
        jQuery(window).scroll(function() {
            scrollBanner();
        });
    }

    jQuery('.tabs_box').tabs();

    jQuery('input[placeholder], textarea[placeholder]').placeholder();

    jQuery('nav ul li:last-child, #members .member:last-child, #stats .stat:last-child, footer .widgets .widget:last-child, footer section.bottom ul li:last-child, .sidebar_widgets .widget li:last-child, .widget_works .works .work:last-child, #works .nav li:last-child').addClass('last');

    jQuery("nav").on('click', '.drops', function () {
        if (jQuery(this).hasClass("active")) {
            jQuery(this).removeClass("active").parent().next().slideUp();
        } else {
            jQuery(this).addClass("active").parent().next().slideDown();
        }
        return false;
    });

    jQuery(document).on('click', "#menu .btn_search", function (e) {
        e.preventDefault()
        jQuery('#menu #search_text').stop(true,true).animate({width:'show'},400).focus();
        jQuery(this).hide(0);
        jQuery('#menu #search_submit').show(0);
    });
    if(!isIE()){
        jQuery(document).on('blur', "#search_form", function (e) {
            e.preventDefault()
            var search_text = jQuery('#search_text').val();
            if(search_text==''){
                jQuery('#menu #search_text').stop(true,true).animate({width:'hide'},100);
                jQuery('#menu .btn_search').show(0);
                jQuery('#menu #search_submit').hide(0);
            }
        });
    }

    jQuery(document).on('click', "#menu_trigger", function (e) {
        e.preventDefault()
        window.setTimeout(function() {
            if(jQuery('#menu nav').hasClass('clicked')){
                jQuery('#menu nav').stop(true,true).animate({height:'hide'},100);
                jQuery('#menu nav').removeClass('clicked');
            }else{
                jQuery('#menu nav').stop(true,true).animate({height:'show'},400);
                jQuery('#menu nav').addClass('clicked');
            }
        }, 400);
        return false;
    });

    jQuery(document).on('click', ".toggle .header", function (e) {
        e.preventDefault()
        var parent = jQuery(this).parent();
        if(!parent.hasClass('current')){
            jQuery('.toggle .box').stop(true,true).slideUp( 300);
            jQuery('.box',parent).stop(true,true).slideDown( 300, function() {
                jQuery('.toggle .item').removeClass('current');
                jQuery(parent).addClass('current');
            });
        }
        return false;
    });


    $('#contact').submit(function() {
        $.ajax({
            type: 'POST',
            url: base_url+"ajaxform.php",
            dataType: 'json',
            data: {
                'action':$(this).attr('id'),
                'f_name':$('#f_name').val(),
                'f_email':$('#f_email').val(),
                'f_subj':$('#f_subj').val(),
                'f_message':$('#f_message').val()
            },
            success: function(data){
                $('#contact').find('fieldset.error').removeClass('error');
                if(data.error){
                    for(var i in data.error){
                        $('#'+data.error[i]).parent().addClass('error');
                    }
                } else {
                    $("#success").show();
                }
            }
        });
        return false;
    });
    if($(".widget_twitter").length){
        $.ajax({
            type: 'POST',
            url: base_url+"ajaxform.php",
            dataType: 'html',
            data: {
                'action':'get_tweets'
            },
            success: function(data){
                $(".widget_twitter ul").html(data);
            }
        });
    }
});
jQuery(window).load(function(){

    jQuery('.widget_flickr ul').magnificPopup({
        delegate: 'a',
        type: 'image',
        gallery: {
            enabled: true, // set to true to enable gallery

            preload: [1,2]
        }
    });
    jQuery('.slider-banner').flexslider({
        animation: "fade",
        directionNav : true,
        slideshow: true,
        slideshowSpeed: 7000,
        animationSpeed: 600
    });
    jQuery('.flexslider').flexslider({
        animation: "fade",
        directionNav : false,
        slideshow: true,
        slideshowSpeed: 7000,
        animationSpeed: 600
    });
    jQuery('#flexslider_project').flexslider({
        animation: "fade",
        controlNav : false,
        slideshow: true,
        slideshowSpeed: 7000,
        animationSpeed: 600
    });
    check_mobile();
    isotope();
});

jQuery(window).resize(function() {
    check_mobile();
});

function isotope(){

    var jQuerycontainer = jQuery('#isotop_container');

    jQuerycontainer.isotope({
        itemSelector : '.isotope-item',
        layoutMode : 'masonry',
        masonry : {
            columnWidth : 1
        },
    });


    var jQueryoptionSets = jQuery('#works .nav.option-set'),
        jQueryoptionLinks = jQueryoptionSets.find('a');

    jQueryoptionLinks.click(function(){
        var jQuerythis = jQuery(this);
        // don't proceed if already selected
        if ( jQuerythis.hasClass('current') ) {
            return false;
        }
        var jQueryoptionSet = jQuerythis.parents('.option-set');
        jQueryoptionSet.find('.current').removeClass('current');
        jQuerythis.addClass('current');

        // make option object dynamically, i.e. { filter: '.my-filter-class' }
        var options = {},
            key = jQueryoptionSet.attr('data-option-key'),
            value = jQuerythis.attr('data-option-value');
        // parse 'false' as false boolean
        value = value === 'false' ? false : value;
        options[ key ] = value;
        if ( key === 'layoutMode' && typeof changeLayoutMode === 'function' ) {
            // changes in layout modes need extra logic
            changeLayoutMode( jQuerythis, options )
        } else {
            // otherwise, apply new options
            jQuerycontainer.isotope( options );
        }

        return false;
    });
}

// Calcualte the home banner parallax scrolling
function scrollBanner() {
    //Get the scoll position of the page
    scrollPos = jQuery(this).scrollTop();

    //Scroll and fade out the banner text
    jQuery('#home_header .slides .slide .info, #home_header .flex-control-nav').css({
        'margin-top' : -(scrollPos/3)+"px",
        'opacity' : 1-(scrollPos/300)
    });

    //Scroll the background of the banner
    jQuery('#home_header .slides .slide').css({
        'background-position' : 'center ' + (scrollPos/4)+"px"
    });
}

// Mobile Detection
function isMobile(){
    return (
        (navigator.userAgent.match(/Android/i)) ||
        (navigator.userAgent.match(/webOS/i)) ||
        (navigator.userAgent.match(/iPhone/i)) ||
        (navigator.userAgent.match(/iPod/i)) ||
        (navigator.userAgent.match(/iPad/i)) ||
        (navigator.userAgent.match(/BlackBerry/))
    );
}

function check_mobile(){
    var winWidth=jQuery(window).width();
    if (winWidth>768) {
        jQuery("#menu nav").show(0)
        jQuery("#menu nav .drop").removeAttr("style")
    }else{
        jQuery("#menu nav").hide(0);
        jQuery('#menu nav').removeClass('clicked');
    }
    if (winWidth<=1100) {
        jQuery("#works .block").removeClass('works');
    }else{
        jQuery("#works .block").addClass('works');
    }

    jQuery('#flexslider_margin').width(jQuery('#flexslider_wrapper').width());
}
function isIE () {
    var myNav = navigator.userAgent.toLowerCase();
    return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
}
function isFF () {
    var myNav = navigator.userAgent.toLowerCase();
    return (myNav.indexOf('firefox') != -1) ? true : false;
}