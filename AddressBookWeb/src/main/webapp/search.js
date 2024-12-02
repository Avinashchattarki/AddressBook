document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    if (!searchInput) return; // Only run on pages with search input

    const contactTable = document.getElementById('contactList');
    if (!contactTable) return;

    const rows = contactTable.getElementsByTagName('tbody')[0].getElementsByTagName('tr');

    searchInput.addEventListener('keyup', function() {
        const searchTerm = searchInput.value.toLowerCase();

        for (let row of rows) {
            const firstName = row.cells[0].textContent.toLowerCase();
            const lastName = row.cells[1].textContent.toLowerCase();
            const email = row.cells[2].textContent.toLowerCase();
            const phone = row.cells[3].textContent.toLowerCase();
            const address = row.cells[4].textContent.toLowerCase();

            if (firstName.includes(searchTerm) || 
                lastName.includes(searchTerm) || 
                email.includes(searchTerm) || 
                phone.includes(searchTerm) || 
                address.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    });
}); 