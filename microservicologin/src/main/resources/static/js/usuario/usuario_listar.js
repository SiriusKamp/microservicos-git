function visualizar(lista) {
    console.log('lista: ', lista);
    let html = '', index = 0, cor = '';
    html += '<div class="row bg-info py-2 rounded-top-3">';
    html += '<div class="col-sm-12 col-md-5 col-lg-5 col-xl-5 text-left text-uppercase fw-bold titulo-coluna-comum">Nome</div>';
    html += '<div class="col-sm-12 col-md-1 col-lg-1 col-xl-1 text-center text-uppercase fw-bold titulo-coluna-comum">Sexo</div>';
    html += '<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 text-left text-uppercase fw-bold titulo-coluna-comum">E-mail</div>';
    html += '<div class="col-sm-12 col-md-1 col-lg-1 col-xl-1 text-left text-uppercase fw-bold titulo-coluna-comum">Editar</div>';
    html += '<div class="col-sm-12 col-md-1 col-lg-1 col-xl-1 text-left text-uppercase fw-bold titulo-coluna-comum">Excluir</div>';
    html += '</div>';
    lista.forEach(item => {
        if (index % 2 == 0)
            cor = 'row-background-impar';
        else
            cor = 'row-background-par';
        html += '<div class="row border cor-principal-site-bordas ' + cor + '" id="id_row_' + index + '">';
        html += '<div class="col-sm-12 col-md-5 col-lg-5 col-xl-5 text-left titulo-coluna-comum">' + item.nome + '</div>';
        html += '<div class="col-sm-12 col-md-1 col-lg-1 col-xl-1 text-center titulo-coluna-comum">' + item.sexo + '</div>';
        html += '<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 text-left titulo-coluna-comum">' + item.email + '</div>';
        html += '<div class="col-sm-12 col-md-1 col-lg-1 col-xl-1 text-left"></div>';
        html += '<div class="col-sm-12 col-md-1 col-lg-1 col-xl-1 text-left"></div>';
        html += '</div>';
        index++;
    });
    document.getElementById('id_div_conteudo').innerHTML = html;
}

window.onload = function () {
    //alert('Email: ' + JSON.parse(sessionStorage.getItem('user')).email);
    let usuario = new Usuario('', '', '', '', '', 1, '');
    usuario.listar();
};