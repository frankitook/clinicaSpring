//according to loftblog tut
$('.nav li:first').addClass('active');

var showSection = function showSection(section, isAnimate) {
  var
  direction = section.replace(/#/, ''),
  reqSection = $('.section').filter('[data-section="' + direction + '"]'),
  reqSectionPos = reqSection.offset().top - 0;

  if (isAnimate) {
    $('body, html').animate({
      scrollTop: reqSectionPos },
    800);
  } else {
    $('body, html').scrollTop(reqSectionPos);
  }

};

var checkSection = function checkSection() {
  $('.section').each(function () {
    var
    $this = $(this),
    topEdge = $this.offset().top - 80,
    bottomEdge = topEdge + $this.height(),
    wScroll = $(window).scrollTop();
    if (topEdge < wScroll && bottomEdge > wScroll) {
      var
      currentId = $this.data('section'),
      reqLink = $('a').filter('[href*=\\#' + currentId + ']');
      reqLink.closest('li').addClass('active').
      siblings().removeClass('active');
    }
  });
};

$('.main-menu, .scroll-to-section').on('click', 'a', function (e) {
  if($(e.target).hasClass('external')) {
    return;
  }
  e.preventDefault();
  $('#menu').removeClass('active');
  showSection($(this).attr('href'), true);
});

$(window).scroll(function () {
  checkSection();
});

function mostrarTabla() {
  document.getElementById('contenedorTabla').style.display = 'block';
  document.getElementById('contenedorHorarios').style.display = 'none';
}

function mostrarHorarios() {
  document.getElementById('contenedorTabla').style.display = 'none';
  document.getElementById('contenedorHorarios').style.display = 'block';
}

// Evento que se ejecuta cuando se hace clic en el botón "Volver"
window.addEventListener('popstate', function (event) {
  mostrarHorarios(); // Cambia a mostrar los horarios cuando se hace clic en "Volver"
});

document.addEventListener("DOMContentLoaded", function() {
  mostrarTabla();
});



  