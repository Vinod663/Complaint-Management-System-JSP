$(document).ready(function() {
    $('table tr').each(function() {
        const $td = $(this).find('td:nth-child(4)');
        if ($td.text().includes('Pending')) {
            $td.css({
                color: '#856404',
                background: 'rgba(255, 193, 7, 0.1)',
                borderRadius: '4px'
            });
        } else if ($td.text().includes('In Progress')) {
            $td.css({
                color: '#004085',
                background: 'rgba(0, 123, 255, 0.1)',
                borderRadius: '4px'
            });
        } else if ($td.text().includes('Resolved')) {
            $td.css({
                color: '#155724',
                background: 'rgba(40, 167, 69, 0.1)',
                borderRadius: '4px'
            });
        }
    });
});