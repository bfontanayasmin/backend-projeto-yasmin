# BookLet
BookLet - Seu guia literário

BookLet consome a Google Books API para buscar e recomendar livros com base em título, categoria ou preferências do usuário, de maneira rápida e simples.

A Google Books API permite que você acesse uma vasta coleção de livros disponíveis no Google Books, possibilitando buscas por título, autor, categoria, entre outros. A API fornece dados detalhados sobre os livros, como título, autores, descrição, categoria, links de imagens da capa e muito mais. Com ela, é possível realizar pesquisas e obter informações sobre livros de forma eficiente.


Disponível em: http://localhost:8000




* Sobre as rotas:

  GET /api/livros/{titulo} -- GET http://localhost:8000/api/livros/extraordinario
  Busca livros por título.
  Resposta:[
	{
		"titulo": "EXTRAORDINÁRIO",
		"autores": [
			"R. J. Palacio"
		],
		"descricao": "Lançado no Brasil originalmente em 2013, Extraordinário é daqueles livros únicos, marcantes, cuja mensagem ressoa com a mesma força não importa quantas vezes você leia. A história de um menino de aparência incomum, mas de coragem e coração enormes: August Pullman, o Auggie, que nasceu com uma severa deformidade facial e agora, aos 10 anos, vai frequentar a escola pela primeira vez. Um grande passo para qualquer criança e maior ainda para o garotinho cujo maior desejo é ser invisível. Mas Auggie, definitivamente, não nasceu para deixar de ser notado. Enquanto tenta vencer seus medos e se integrar em um mundo completamente novo, sua presença desencadeia as mais diferentes reações — algumas boas, outras nem tanto, mas todas profundamente transformadoras. Um romance comovente, poderoso e impossível de ignorar. Adaptado para os cinemas e estrelado pelo jovem Jacob Tremblay e por Julia Roberts, Extraordinário é relançado nesta edição especial comemorativa do filme, com novo posfácio da autora, fotos de bastidores e depoimentos que dão conta do impacto da mensagem de Extraordinário na vida dos artistas envolvidos na megaprodução. Um conteúdo inédito e exclusivo.",
		"capaUrl": null,
		"categoria": "Fiction"
	}




  GET /api/genero/{categoria} -- GET http://localhost:8000/api/genero/drama
  Busca livros por categoria.
  Resposta:
  
	{
		"titulo": "Twelfth Night, Or, What You Will",
		"autores": [
			"William Shakespeare"
		],
		"descricao": "Twelfth Night is one of the most popular of Shakespeare's plays in the modern theatre, and this edition places particular emphasis on its theatrical qualities throughout. The introduction analyses the many views of love in the play, and the juxtaposition of happiness and melancholy used to dramatize them. The presentation of the text has been re-thought in theatrical terms, and the exceptionally full an detailed commentary pays close attention to the often difficult language. The play's contrastig moods are emphasized by the use of music, which plays an important role in Twelfth Night; James Walker has re-edited the existing music from the original sources, and where none exist has composed settings compatible in style with the surviving originals, so that this edition offers material for all the music required in a performance, the only modern edition to do so. The edition will be invaluable to actors, directors, and students at all levels.",
		"capaUrl": "http://books.google.com/books/content?id=4SESDAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
		"categoria": "Drama"
	},



  POST /api/recomendar
  Recomenda livros com base nas preferências do usuário (passar as preferências como JSON no corpo da requisição).
  Exemplo: POST http://localhost:8000/api/recomendar
Content-Type: application/json

{
  "preferencias": ["fiction", "drama"]
}

Resposta:
[
  {
    "titulo": "Livro 1",
    "autores": ["Autor 1"],
    "descricao": "Descrição do livro",
    "capaUrl": "https://link-da-capa.com",
    "categoria": "Fantasia"
  },
  {
    "titulo": "Livro 2",
    "autores": ["Autor 2"],
    "descricao": "Descrição do livro",
    "capaUrl": "https://link-da-capa.com",
    "categoria": "Aventura"
  }
]


  GET /api/sobre -- GET http://localhost:8000/api/sobre
  Retorna informações sobre o projeto e os integrantes.


  
