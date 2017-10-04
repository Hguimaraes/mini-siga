# Documento de Especificação de Requisitos

<table>
    <tr>
        <td>
        	<strong>Nome do Projeto:</strong>
        	<br>
        	Sistema de Gerenciamento de Disciplinas
        </td>
        <td>
        	<strong>Data de Solicitação:</strong>
        	<br>
        	18/09/2017
        </td>
    </tr>
    <tr>
    	<td colspan="2">
        	<strong>Responsáveis:</strong>
        	<br>
        	Brian Confessor, Heitor Guimarães, Igor Amaral, Marcelo Mastrelli
        </td>
    </tr>
    <tr>
    	<td>
        	<strong>Solicitante:</strong>
        	<br>
        	Toacy Cavalcante de Oliveira
        </td>
        <td>
        	<strong>Cliente:</strong>
        	<br>
        	Toacy Cavalcante de Oliveira
        </td>
    </tr>
</table>

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