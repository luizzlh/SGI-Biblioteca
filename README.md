Projeto: Sistema de Gestão de Biblioteca 📚
Descrição:
Você desenvolverá um sistema para gerenciar uma biblioteca. O sistema permitirá que usuários façam empréstimos de livros, acompanhem o status dos livros (disponível, emprestado), e que o administrador da biblioteca gerencie o catálogo de livros e os registros de empréstimos.

Funcionalidades Requeridas:

Catálogo de Livros:
Adicionar, remover e listar livros no catálogo.
Cada livro terá: Título, Autor, ISBN, e Estado (Disponível/Emprestado).

Gestão de Usuários:
Cadastrar usuários com nome e número de identificação.
Listar todos os usuários registrados.
Empréstimo de Livros:

Um usuário pode emprestar um livro, desde que ele esteja disponível.
Registrar a data do empréstimo.
Atualizar o estado do livro para "Emprestado".
Devolução de Livros:

Registrar a devolução de um livro e atualizar o estado para "Disponível".
Tratamento de Exceções:

Não permitir emprestar um livro já emprestado (lançar uma exceção).
Lançar exceção ao tentar registrar um usuário ou livro duplicado.
Capturar e tratar erros de entrada de dados inválidos.
Requisitos Técnicos:
Use classes e objetos para estruturar o sistema.
Aplique encapsulamento com getters e setters.
Use herança e polimorfismo para diferenciar entre Usuários e Administradores.
Utilize interfaces para definir ações comuns (ex.: Cadastro e Listagem).
Aplique composição para modelar o relacionamento entre a Biblioteca, Livros e Usuários.
Inclua arrays ou coleções (ex.: ArrayList) para armazenar usuários e livros.
Implemente exceções personalizadas para cenários específicos, como "Livro Não Disponível" ou "Usuário Não Encontrado".
Extras (Opcional):
Implemente um sistema simples de busca por livros usando o título ou ISBN.
Salve os dados em arquivos para que sejam recuperados ao reiniciar o programa.
