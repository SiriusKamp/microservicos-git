function visualizarAlert(json) {
    document.getElementById('id_div_alert').style.display = 'block'; //visualiza o alert na tela de login.
    document.getElementById('id_alert').innerText = 'Mensagem: ' + json.mensagem; //insere a resposta do servidor no alert da tela de login.
    window.setTimeout(() => { document.getElementById('id_div_alert').style.display = 'none'; }, 3000); //aguarda o tempo de 3 segundos para fechar automaticamente o alert da tela de login.
}