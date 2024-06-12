function logout() {
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = '/members/logout';
    document.body.appendChild(form);
    form.submit();
}