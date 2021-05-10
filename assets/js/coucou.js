import 'jquery';
import 'materialize-css';

import 'materialize-css/dist/css/materialize.min.css';
import '../css/style.css';

/* Activate sidebar nav */
$('#sidenav a').click(function() {
    $(this).siblings('a').removeClass('active');
    $(this).addClass('active');
});

/* Activate tooltips */
$(document).ready(function () {
    $('.tooltipped').tooltip({enterDelay:500, position: 'right'});
});

/* Activate collapsible sections */
$(document).ready(function () {
    $('.collapsible').collapsible();
});
