# Documento de Especificação de Requisitos

| **Nome do Projeto:** | Sistema de Gerenciamento de Disciplinas |
| ----- | ----- |
| **Data de Solicitação:** | 18/09/2017 |
| **Responsáveis:** | Brian Confessor, Heitor Guimarães, Igor Amaral, Marcelo Mastrelli |
| **Solicitante:** | André Brito |
| **Cliente:** | Toacy Cavalcante de Oliveira |


## Versões e Revisões deste documento

| Data | Comentário | Autor | Versão |
| ----- | ----- | ----- | -----|
| 27/09/2017 | Levantamento de requisitos após primeira conversa com stakeholder | Brian, Heitor, Igor e Marcelo | 1.0 |
| 02/10/2017 | Elaboração inicial dos requisitos | Brian, Heitor, Igor e Marcelo | 1.1 |
| 04/10/2017 | Correção de requisitos funcionais e não funcionais após conversa com o QA | Brian, Heitor, Igor e Marcelo | 1.2 |

1. **Introdução**

	O objetivo deste documento é de definir claramente, acordado bilateralmente entre o gerente e o stakeholder, quais são os requisitos funcionais e não funcionais do sistema proposto. Também é importante ressaltar os usuários envolvidos para a utilização do sistema de gerenciamento de disciplinas de pós-graduação da COPPE. 

	1. **Descrição de Usuários**
		O usuário do sistema pode assumir um dos seguintes papéis:

		* **Aluno:** É o usuário básico do sistema, podendo visualizar as turmas abertas das disciplinas e se inscrever e trancar nas desejadas para aquele trimestre.

		* **Funcionário:** Tem o papel de super admin do sistema. Pode ser Secretaria ou Professor.

	2. **Visão Geral do Produto**

		A principal ideia é que o Sistema de Gerenciamento de Disciplinas possa ser utilizado como uma aplicação Web, onde a infraestrutura para a execução será transparente ao usuário, ou seja, basta que ele possua um navegador moderno disponível. Algumas das características e funções do produto incluem (mas não se restringem):

		* Cadastrar, editar e excluir disciplinas
		* Inscrever alunos em disciplinas existentes
		* Lançar notas de alunos que cursaram determina disciplina
		* Trancar a inscrição de um aluno em uma determinada disciplina
		* Determinar o calendário de inscrição e trancamento em disciplinas

2. **Requisitos do Sistema/Software**

	1. **Requisitos Funcionais (RF):**

	| Código | Descrição do Requisito Funcional | Situação | Prioridade |
	| ----- | ----- | ----- | ----- |
	| RF01 | O sistema deve permitir que o usuário Funcionário crie uma nova disciplina. | Proposto | Alta |
	| RF02 | O sistema deve permitir que o usuário Funcionário altere os dados de uma disciplina já cadastrada no sistema, ou a exclua| Proposto | Alta |
	| RF03 | O sistema deve permitir que o usuário Funcionário crie uma turma de uma determinada disciplina. | Proposto | Alta |
	| RF04 | O sistema deve permitir que o usuário Funcionário altere os dados de uma turma existente. | Proposto | Alta |
	| RF05 | O sistema deve permitir que uma turma cujas aulas não tenham sido iniciadas seja excluídas por um usuário Funcionário. | Proposto | Alta |
	| RF06 | O sistema deve permitir que os usuário Aluno visualize a listagem de turmas disponíveis no período de inscrição em disciplinas | Proposto | Alta |
	| RF07 | O sistema deve permitir que o usuário Aluno possa realizar a inscrição em uma turma de uma determinada disciplina com vagas disponíveis durante o período de inscrição em disciplinas. | Proposto | Alta |
	| RF08 | O sistema deve permitir que o usuário Aluno tranque sua inscrição em uma turma durante o período de trancamento de disciplinas. | Proposto | Alta |
	| RF09 | O sistema deve permitir que o usuário Funcionário possa realizar o lançamento de notas para cada Aluno inscrito em uma determinada turma. | Proposto | Alta |
	| RF10 | O sistema deve permitir que o usuário Funcionário inscreva os Alunos diretamente nas turmas independente do período de inscrição em disciplinas. | Proposto | Média |
	| RF11 | O sistema deve permitir que o usuário Funcionário tranque a inscrição em uma turma de qualquer aluno diretamente, independente do Período de Trancamento de Disciplinas. | Proposto | Média
	| RF12 | O sistema deve permitir que o usuário Funcionário estipule os períodos de inscrição e trancamento de disciplinas. | Proposto | Alta |
	| RF13 | O sistema deve notificar o usuário Aluno quando ele realizar inscrição ou trancamento em uma turma. | Proposto | Média |
	| RF14 | O sistema deve notificar o usuário Aluno quando uma turma na qual ele estiver inscrito seja excluída do sistema. | Proposto | Média |
	| RF15 | O sistema deve notificar o usuário Aluno quando o mesmo reprovar uma disciplina | Proposto | Média |

	2. **Requisitos Não-Funcionais (RNF)**

	| Tipo de RNF | Código | Descrição do Requisito Funcional | Situação | Prioridade |
	| :-----: | ----- | ----- | ----- | ----- |
	| **Comunicação de Dados, Interface e Interoperabilidade** | RNF01 | O sistema deve fazer integração com plataforma de email. | Proposto | Média |
	| **Confiabilidade** | RNF02 | O sistema deve manter as inscrições dos alunos e notas lançadas mesmo quando não estiver disponível. | Proposto | Alta |
	| **Desempenho e Robustez** | RNF03 | O sistema deve permitir o acesso simultâneo de pelo menos 50% dos alunos. | Proposto | Média |
	| **Disponibilidade** | RNF04 | O sistema deve estar disponível 95% do tempo. | Proposto | Média |
	| **Portabilidade** | RNF05 | O sistema deverá rodar em pelo menos dois Browsers diferentes, sendo eles preferencialmente o Mozilla Firefox e o Google Chrome. | Proposto | Média |
	| **Segurança** | RNF06 | Os dados de um Aluno não podem ser visíveis para outros Alunos do Sistema. | Proposto | Alta |
	| **Segurança** | RNF07 | Um usuário só poderá utilizar o sistema caso esteja autenticado no mesmo. | Proposto | Alta |
	| **Segurança** | RNF08 | Alunos não podem realizar inscrição em uma turma de disciplina que está sendo cursada ou nas quais ele já obteve aprovação. | Proposto | Alta |
	| **Segurança** | RNF09 | Alunos não podem realizar inscrição em duas turmas com horários simultâneos. | Proposto | Alta |
	| **Usabilidade** | RNF10 | A listagem de disciplinas disponíveis pode ser em ordem alfabética ou de disponibilidade de vagas. | Proposto | Média |
	| **Restrições de Projeto e Tecnológicas** | RNF11 | O sistema deve ser implementado utilizando a tecnologia JHipster. | Proposto | Alta |
	| **Restrições de Projeto e Tecnológicas** | RNF12 | O sistema deve ser implementado utilizando a linguagem Java para Web | Proposto | Alta |
	| **Restrições de Projeto e Tecnológicas** | RNF13 | O Banco de Dados adotado para o sistema é o PostrgresSQL | Proposto | Alta |
	| **Restrições de Projeto e Tecnológicas** | RNF14 | O controle de versionamento do projeto será realizado no GitHub | Proposto | Alta |
	| **Restrições de Projeto e Tecnológicas** | RNF15 | O gerenciamento do projeto será utilizado por meio do Redmine | Proposto | Alta |

	3. Glossário

	* __Disciplina:__ Matéria oferecida pela Universidade, que consiste de um código alfanumérico composto de três letras e três número, nome, ementa e carga horária.
	* __Turma:__ Instância de uma disciplina oferecida em um determinado período. Toda turma possui um código de identificação, um professor, número de vagas, horário de início e fim e uma lista de alunos inscritos.
	* __Período de Inscrição em Disciplinas:__ Período fixo no começo de cada trimestre que permite que um aluno se inscreva em turmas de disciplinas oferecidas naquele período letivo.
	* __Período de Trancamento em Disciplinas:__ Período fixo, com início imediatamente após o período de inscrição em disciplinas que permite a exclusão da participação de uma turma para cada Aluno.
	* __Notas:__ Avaliação de desempenho de um determinado aluno em uma disciplina. Pode variar entre A, B, C e D, sendo A a maior nota possível de ser alcançada e D sendo o pior conceito alcançável, significando que o aluno reprovou aquela determinada disciplina.

