# Projeto_Presenca_FAP
O script inicialmente realiza o acesso da plataforma por meio da função get() direcionando para a URL definida. Em seguida, os campos de usuário/n° da matrícula e senha são preenchidos (obviamente utilizei os meus dados para o acesso à plataforma) e o login é efetuado. Após o login utilizo a função assertEquals() e comparo os dados do topo direito da página, com os dados referentes a meu usuário, para me certificar que a conta logada é a correta. O menu lateral é encontrado e por meio dele a central de faltas é acessada. Por fim, o script registra o nome e a porcentagem de falta em dois arrays, assim utilizo a função arquivo() para a criação e registro das informações encontradas em um arquivo .txt . 
