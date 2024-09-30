let form = document.getElementById('id_form_login'); //A variável form assume o comportamento da tag <form> da página login.html
form.addEventListener('submit', function (e) {
    e.preventDefault(); //isto é para o envio da form.
    let url = this.action; //o url que processa a form.
    let formData = new FormData(this); //os dados da form.
    let ajax = new XMLHttpRequest(); //para enviar uma requisição assíncrona para o servidor
    ajax.open("POST", url, true); //configura e abre a requisição para o servidor
    ajax.onload = function () { //carrega a resposta enviada pelo servidor.
        let resposta = JSON.parse(ajax.responseText); // a resposta do servidor.
        if (ajax.status == 200) { //se a resposta teve bom êxito.
            if (resposta.tipo == 'erro') { //se não encontrou o usuário e/ou senha.
                document.getElementById('id_div_alert').style.display = 'block'; //visualiza o alert na tela de login.
                document.getElementById('id_alert').innerText = 'Mensagem: ' + resposta.mensagem; //insere a resposta do servidor no alert da tela de login.
                window.setTimeout(() => { document.getElementById('id_div_alert').style.display = 'none'; }, 3000); //aguarda o tempo de 3 segundos para fechar automaticamente o alert da tela de login.
            } else if (resposta.tipo == 'sucesso') { //se o usuário e senha foram encontrados.
                //Criando o json para inserir alguns dados no sessionStorage do navegador.
                let json = { 'email': resposta.email, 'grupo': resposta.grupo, 'nome': resposta.nome, 'sexo': resposta.sexo };
                //Convertendo o objeto json para string e inserindo no sessionStorage.
                sessionStorage.setItem('user', JSON.stringify(json));
                //Redirecionando para o url /listarUsuario do sistema.
                window.location.replace('/listarUsuario');
            }
        } else {
            document.getElementById('id_div_alert').style.display = 'block'; //visualiza o alert na tela de login.
            document.getElementById('id_alert').innerText = 'Mensagem: ' + resposta.mensagem; //insere a resposta do servidor no alert da tela de login.
            window.setTimeout(() => { document.getElementById('id_div_alert').style.display = 'none'; }, 3000); //aguarda o tempo de 3 segundos para fechar automaticamente o alert da tela de login.
        }
    };
    ajax.send(formData); //envia a requisição para o servidor.
});