# Documento de Visão do Sistema

> O objetivo deste documento é definir para o cliente a visão do produto a ser desenvolvido, especificado em termos da necessidade do cliente. Neste documento, vamos apresentar as características gerais do sistema, o que servirá de apoio para a elaboração de documentos técnicos do projeto.

## Equipe:

* Brian Confessor
* Heitor Guimarães
* Igor Amaral
* Marcelo Maestrelli


1. *Posicionamento*

    1. *Oportunidade de Negócio*

		Estima-se que a UFRJ possui em torno de 11 mil alunos de pós-graduação divididos entre mestrado, mestrado profissional e doutorado. Entretanto, existe um grande problema logístico referente ao cadastramento de alunos ingressantes, de posicionamento desses alunos em linhas de pesquisa e de gerenciamento de suas vidas acadêmicas. Dito isso, o sistema proposto por esse documento visa ser um sistema de auxílio no gerenciamento da vida acadêmica de um aluno de pós-graduação, para uso tanto do aluno quanto da secretaria da pós-graduação.

    2. *Descrição do problema*

		Um ano letivo na pós-graduação é dividido em três trimestres, sendo que em cada um destes é necessária uma nova inscrição em disciplinas. Para que isso seja possível, as disciplinas precisam ser previamente abertas pela secretaria do curso pois elas possuem uma lista com as classes que serão ministradas a cada trimestre. Assim, ela define uma sala com uma determinada capacidade para cada classe, permitindo que os alunos possam, finalmente, se inscrever em cada disciplina que pretendem cursar, respeitando o número de vagas.

		Porém, todo esse processo gera alguns transtornos para os alunos e as secretarias dos cursos, acarretando em um atraso no início do período letivo em si, intensificado pelo fato de que metade da duração dos cursos de pós-graduação é teórica.

		Uma solução para esse problema poderia ser um sistema de auxílio de inscrição de disciplinas, que facilitasse o trabalho de criação de disciplinas para a secretaria e a inscrição em disciplinas propriamente dita para os alunos, tirando assim grande parte da carga de trabalho da secretaria do curso, agilizando todo o processo de inscrição em disciplinas. 

2. *Características do Produto*

	O sistema deve permitir a criação de disciplinas pela secretaria, que precisam de um nome, código composto por 3 (três) letras e 3 (três) números, um professor, uma sala e um horário pré determinado. Além disso, o sistema também deverá permitir a alteração de dados de uma disciplina pré-cadastrada e sua exclusão.

	O sistema também deverá permitir que um aluno recupere a lista de todas as disciplinas que serão ofertadas naquele trimestre e escolha as disciplinas nas quais ele vai se inscrever. Além disso, o aluno também poderá excluir a inscrição em uma determinada disciplina e visualizar todas as disciplinas nas quais ele se inscreveu naquele trimestre.

	Da mesma forma, o sistema deverá permitir que funcionários (professores ou a secretaria) lancem as notas de um determinado aluno, que podem variar de A a D. Para que um aluno seja aprovado em determinada disciplina, ele deve alcançar no mínimo nota C em uma determinada disciplina.

	Um aluno não poderá se inscrever em nenhuma disciplina na qual já tenha sido aprovado. Caso um aluno reprove uma disciplina, ele obrigatoriamente deverá cursá-la novamente. Caso ele reprove a disciplina novamente, o sistema deverá informar à secretaria que aquele aluno está em condição de jubilamento.

	Do mesmo modo, não é permitido que um aluno curse duas disciplinas ao mesmo tempo. Portanto, o sistema deverá emitir um alerta ao aluno caso o mesmo encontre-se nessa situação.

	Caso o número de vagas de uma determinada disciplina se esgote, o sistema deverá colocar os alunos que se inscreverem depois em uma fila. Caso algum aluno que já estava inscrito na disciplina venha a excluir sua inscrição, o primeiro aluno da fila deverá ser inscrito na disciplina, e o sistema deverá informar ao aluno que ele conseguiu alocação na turma.
