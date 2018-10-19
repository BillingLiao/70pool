

    $.fn.clicktoggle = function(a, b) {
        return this.each(function() {
            var clicked = false;
            $(this).click(function() {
                if (clicked) {
                    clicked = false;
                    return b.apply(this, arguments);
                }
                clicked = true;
                return a.apply(this, arguments);
            });
        });
    };

    document.createElement("gist");

    $.fn.ready(function() {
      $('.default').dropkick();

      $('.black').dropkick({
        theme : 'black'
      });

      $('.change').dropkick({
        change: function (value, label) {
          alert('You picked: ' + label + ':' + value);
        }
      });

      $('.existing_event').dropkick();

      $('.custom_theme').dropkick({
        theme: 'black',
        change: function (value, label) {
          $(this).dropkick('theme', value);
        }
      });

      $('.dk_container').first().focus();

      $('#enable_sample').clicktoggle(
        function() {
          $('[name="country-disabled"]').dropkick('disable',false);
          $(this).html('Disable')
        },
        function() {
          $('[name="country-disabled"]').dropkick('disable');
          $(this).html('Enable')
        }
      );
    });