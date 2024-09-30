function listarObjeto(method, url, formData, callback) {
    let ajax = new XMLHttpRequest(); //para enviar uma requisição assíncrona para o servidor
    ajax.open(method, url, true); //configura e abre a requisição para o servidor
    ajax.send(formData); //envia a requisição para o servidor.
    ajax.onload = function () { //carrega a resposta enviada pelo servidor.
        if (callback)
            callback(ajax);
    };
}