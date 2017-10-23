$(function() {
    var startDate;
    var endDate;
    
    var selectCurrentWeek = function() {
        window.setTimeout(function () {
            $('#weekpicker').datepicker('widget').find('.ui-datepicker-current-day a').addClass('ui-state-active')
        }, 1);
    }
    
    $('#weekpicker').datepicker( {
    	firstDay: 1,
        showOtherMonths: false,
        selectOtherMonths: false,
        onSelect: function(dateText, inst) { 
            var date = $(this).datepicker('getDate');
            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() +1);
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
            var dateFormat = "dd.mm.yy";
            $('#weekpicker').val($.datepicker.formatDate( dateFormat, startDate, inst.settings )
                 + ' - ' + $.datepicker.formatDate( dateFormat, endDate, inst.settings ));
            
            var tuesday = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() +2);
            var wednesday = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() +3);
            var thursday = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() +4);
            var friday = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() +5);
            var saturday = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() +6);
            
            $('#fullWeek').val($.datepicker.formatDate( dateFormat, startDate, inst.settings )
            		+ '-' + $.datepicker.formatDate( dateFormat, tuesday, inst.settings ) 
            		+ '-' + $.datepicker.formatDate( dateFormat, wednesday, inst.settings ) 
            		+ '-' + $.datepicker.formatDate( dateFormat, thursday, inst.settings ) 
            		+ '-' + $.datepicker.formatDate( dateFormat, friday, inst.settings ) 
            		+ '-' + $.datepicker.formatDate( dateFormat, saturday, inst.settings ) 
                    + '-' + $.datepicker.formatDate( dateFormat, endDate, inst.settings ));
            selectCurrentWeek();
        },
        beforeShow: function() {
            selectCurrentWeek();
        },
        beforeShowDay: function(date) {
            var cssClass = '';
            if(date >= startDate && date <= endDate)
                cssClass = 'ui-datepicker-current-day';
            return [true, cssClass];
        },
        onChangeMonthYear: function(year, month, inst) {
            selectCurrentWeek();
        }
    }).datepicker('widget').addClass('ui-weekpicker');
    
    $('.ui-weekpicker .ui-datepicker-calendar tr').bind('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('.ui-weekpicker .ui-datepicker-calendar tr').bind('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
});