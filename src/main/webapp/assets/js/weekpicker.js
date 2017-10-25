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
            var dateFormat = "d.m.yy";
            $('#weekpicker').val($.datepicker.formatDate( dateFormat, startDate, inst.settings )
                 + '-' + $.datepicker.formatDate( dateFormat, endDate, inst.settings ));
            var res = $.datepicker.formatDate( dateFormat, startDate, inst.settings ) + '-' + $.datepicker.formatDate( dateFormat, endDate, inst.settings );
            console.log(res);
            $('#weekpickerForm').attr({'action' : '/todo/showSelectedWeek/'+ res});
            
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