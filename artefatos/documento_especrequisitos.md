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
	| RF04 | O sistema deve permitir que o usuário Funcionário edite uma turma existente | Proposto | Alta |
	| RF05 | O sistema deve permitir que uma turma cujas aulas não tenham sido iniciadas seja excluídas por um usuário Funcionário. | Proposto | Alta |
	| RF06 | O sistema deve permitir que os usuário Aluno visualize a listagem de turmas disponíveis no período de inscrição em disciplinas | Proposto | Alta |
	| RF07 | O sistema deve permitir que o usuário Aluno possa realizar a inscrição em uma turma de uma determinada disciplina com vagas disponíveis durante o período de inscrição em disciplinas[a]. | Proposto | Alta |
	| RF08 | O sistema deve permitir que o usuário Aluno tranque sua inscrição em uma turma durante o período de trancamento de disciplinas[b]. | Proposto | Alta |
	| RF09 | O sistema deve permitir que o usuário Funcionário possa realizar o lançamento de notas para cada Aluno inscrito em uma determinada turma. | Proposto | Alta |
	| RF10 | O sistema deve permitir que o usuário Funcionário inscreva os Alunos diretamente nas turmas independente do período de inscrição em disciplinas[a]. | Proposto | Média |
	| RF11 | O sistema deve permitir que o usuário Funcionário tranque a inscrição em uma turma de qualquer aluno diretamente, independente do Período de Trancamento de Disciplinas[b]. | Proposto | Média
	| RF12 | O sistema deve permitir que o usuário Funcionário estipule os períodos de inscrição e trancamento de disciplinas[a][b]. | Proposto | Alta |
	| RF13 | O sistema deve notificar o usuário Aluno quando ele realizar inscrição ou trancamento em uma turma. | Proposto | Média |
	| RF14 | O sistema deve notificar o usuário Aluno quando uma turma na qual ele estiver inscrito seja excluída do sistema. | Proposto | Média |

	2. **Requisitos Não-Funcionais (RNF)**
	