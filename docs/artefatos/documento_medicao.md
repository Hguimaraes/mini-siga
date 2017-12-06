# Plano de Medição Organizacional

**Versão do Documento:** 2.0 **Data: 01/12/2017**

**Participantes do Planejamento da Medição:**
* Brian Confessor
* Heitor Guimarães
* Igor Amaral
* Marcelo Maestrelli

1. **Escopo**

	1. **Contexto Organizacional:**

		O projeto **Mini-Siga** visa permitir uma melhor manutenção da grade horária do aluno de pós-graduação da UFRJ. De maneira mais específica, o sistema deve permitir a criação, alteração e exclusão de disciplinas pela secretaria. Ele deve também permitir que um aluno recupere uma lista de todas as disciplinas ofertadas no trimestre atual, e permitir que o mesmo se crie e exclua inscrições em uma disciplina.
	
		O desenvolvimento do *software* será realizado utilizando o Jhipster; o gerenciamento das tarefas será realizado no Redmine, e o controle de versionamento será pelo Github. O detalhamento da documentação do projeto se iniciou em **18/09/2017** e o projeto será entregue em **13/11/2017**. Todos os membros da equipe trabalharão com a criação da documentação necessária do projeto, o desenvolvimento do *software* e os testes do mesmo. Os documento de apoio do desenvolvimento do projeto (Documento de Visão, de Requisitos, Casos de Uso, etc) encontram-se no Github do projeto, junto com os demais arquivos.

	2. **Problemas:**

		Muitos requisitos para serem tratados pelo número relativamente pequeno de membros da equipe. Apontar quais os requisitos mais importantes a serem implementados é primordial. O tempo para o desenvolvimento do projeto é curto, portanto uma boa manutenção do tempo de cada membro é essencial.

		A tecnologia utilizada para o desenvolvimento é relativamente nova para todos os membros, e somando isso ao curto tempo e ao grande número de requisitos, uma boa delegação de tarefas que faça bom uso dos conhecimentos individuais de cada membro a fim de concluir com maior otimização e facilidade cada tarefa apresentada é imprescindível.

	3. **Motivação:**

		A importância da medição para a organização foi reconhecida pela alta gerência. O uso da medição ao longo do tempo de vida do projeto será útil para adaptar o processo quando se mostrar necessário, a fim de se alcançar o objetivo final.

	4. **Glossário de Termos:**

2. **Plano GQ(I)M**

	1. **Modelo GQ(I)M:**

	![Imagem do Modelo GQ(I)M](imgs/MED001.png "Modelo GQ(I)M")
	![Legenda do Modelo GQ(I)M](imgs/MED002.png "Legenda do Modelo GQ(I)M")

	2. **Definição operacional de indicadores e respectivas medidas:**

		**Indicadores:**

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Índice de Associação ao Processo** |
		| Mnemônico | IAP |
		| Definição | Quantifica a porcentagem de tarefas do projeto relacionadas às atividades do processo |
		| Objetivos | Caracterizar quantidade de tarefas sem associação com atividades do processo |
		| Questões | Nos dá a quantidade de tarefas atreladas ao processo. |
		| Entidades mensuráveis | Projeto |
		| Elemento mensurável | Adequação |
		| Unidade de medida | Percentual (%) |
		| Intervalo de dados | [0, 1] |
		| Tipo de escala | Razão |
		| Valores da escala | 0-100% |
		| Função de medição | IAP = QAAP/QTAP<br><br>onde:<br><br>QAAP - Quantidade de Atividades Associadas ao Processo<br>QTAP - Quantidade Total de Atividades do Projeto |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Modelo de Interpretação | Quanto maior o valor, mais adequado ao processo o projeto é. |
		| Critério de decisão | Se estiver abaixo de 80%, procurar ajustar as tarefas para se adequarem melhor ao processo. |
		| Procedimento de análise | Contabilizar a quantidade de Atividades Associadas ao Processo existentes, e dividir seu valor pela Quantidade Total de Atividades do Projeto. |
		| Restrições | Nenhuma. |
		| Agente | Gerente de Projeto |
		| Fonte | Criado pelos Autores |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Índice de Inadequação ao Processo** |
		| Mnemônico | IIP |
		| Definição | Caracteriza quantidade de atividades do processo sem relação com tarefas. |
		| Objetivos | Caracterizar a quantidade de atividades do processo sem relação com tarefas |
		| Questões | Nos diz se o processo utilizado é adequado ao projeto atual. |
		| Entidades mensuráveis | Projeto |
		| Elemento mensurável | Adequação |
		| Unidade de medida | Percentual (%) |
		| Intervalo de dados | [0,1] |
		| Tipo de escala | Razão |
		| Valores da escala | 0-100% |
		| Função de medição | IIP = QANIP/QADP<br>Br>onde:<br><br>QANIP - Quantidade de Atividades Não Instanciadas no Projeto<br>QADP - Quantidade de Atividades Definidas no Processo  |
		| Momento | Ao fim da iteração. |
		| Periodicidade | Uma vez a cada iteração. |		
		| Modelo de Interpretação | Quanto maior o valor, mais inadequado ao projeto o processo é. |
		| Critério de decisão | Se estiver acima de 20%, procurar ajustar as tarefas para se adequarem melhor ao processo. Se estiver acima de 50%, considerar a possibilidade de utilizar outro processo para a execução do projeto. |
		| Procedimento de análise | Contabilizar a Quantidade de Atividades Não Instanciadas no Processo, e dividir pela Quantidade de Atividades Definidas no Processo. |
		| Restrições | Nenhuma. |
		| Agente | Gerente de Projeto |
		| Fonte | Criado pelos Autores |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Índice de Defeitos Encontrados na Fase de Testes** |
		| Mnemônico | In_Dfi |
		| Definição | Caracteriza número de defeitos descobertos em revisão por artefato. |
		| Objetivos | Caracterizar número de defeitos descobertos em revisão por artefato |
		| Questões | Nos diz se houveram muitos testes defeituosos entre os testes executados, ou seja, se há erros no código, seja na sua concepção, seja no seu fluxo. |
		| Entidades mensuráveis | Artefato |
		| Elemento mensurável | Qualidade |
		| Unidade de medida | Percentual (%) |
		| Intervalo de dados | [0,1] |
		| Tipo de escala | Razão. |
		| Valores da escala | 0-100% |
		| Função de medição | In_Dfi = TST_Dfi/TSTi<br>Br>onde:<br><br>TST_Dfi - Número de Testes identificados com Defeitos na iteração i<br>TSTi - Número de Testes realizados na iteração i |
		| Momento | Após a realização de cada fase de testes. |
		| Periodicidade | Uma vez por fase de testes. |
		| Modelo de Interpretação | Quanto maior a porcentagem, pior o desempenho do software construído na bateria de testes. |
		| Critério de decisão | Se for maior de 40%, reavaliar os algoritmos e a concepção dos testes, e se necessário dos requisitos cujos testes falharam. |
		| Procedimento de análise | Contabilizar o Número de Testes Identificados com Defeitos na Iteração i, e dividir pelo Número de Testes Realizados na Iteração i |
		| Restrições | Nenhuma |
		| Agente | Equipe de Desenvolvimento |
		| Fonte | Adaptado de Pgoraro (2014) |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Índice de Densidade de Defeitos** |
		| Mnemônico | IDD |
		| Definição | Caracterizar densidade de defeitos encontrados em testes de Software. |
		| Objetivos | Caracterizar densidade de defeitos encontrados em testes de Software |
		| Questões | Analisar quais iterações do projeto obtiveram o maior número de defeitos encontrados. |
		| Entidades mensuráveis | Artefato |
		| Elemento mensurável | Qualidade |
		| Unidade de medida | Percentual (%) |
		| Intervalo de dados | [0,1] |
		| Tipo de escala | Razão. |
		| Valores da escala | 0-100% |
		| Função de medição | IDD = In_Dfi/TCP<br>Br>onde:<br><br>In_Dfi - Índice de Defeitos Encontrados na Fase de Testes i<br>TCP - Total de Classes do Projeto |
		| Momento | Ao fim de cada fase de testes. |
		| Periodicidade | Uma vez por fase de testes. |
		| Modelo de Interpretação | Quanto menor, melhor |
		| Procedimento de análise | Contabilizar o Índice de Defeitos Encontrados na Fase de Testes i, para cada i existente, e dividir pelo Total de Classes do Projeto. |
		| Restrições | O Índice de Defeitos Encontrados na Fase de Teste deverá ser calculado primeiro |
		| Agente | Equipe de Desenvolvimento |
		| Fonte | Adaptado de Pgoraro (2014) |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Índice de Tarefas Concluídas na Iteração i** |
		| Mnemônico | In_TCi |
		| Definição | Caracteriza percentual de tarefas entregues dentro do prazo. |
		| Objetivos | Caracterizar percentual de tarefas entregues dentro do prazo |
		| Questões | Avaliar se há uma quantidade aceitável de tarefas sendo entregues dentro do prazo. |
		| Entidades mensuráveis | Projeto |
		| Elemento mensurável | Qualidade |
		| Unidade de medida | Percentual (%) |
		| Intervalo de dados | [0,1] |
		| Tipo de escala | Razão. |
		| Valores da escala | 0-100% |
		| Função de medição | In_TCi = NTCi/NTTi<br><br>onde:<br><br>NTCi - Número de Tarefas Concluídas na Iteração i<br>NTTi - Número Total de Tarefas da Iteração i  |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Modelo de Interpretação | Quanto maior, melhor |
		| Critério de decisão | Se o índice for menor que 50%, avaliar o motivo da baixa quantidade de entregas no prazo; considerar remanejar as tarefas entre os desenvolvedores, ou analisar as mesmas para avaliar se estas são viáveis ou pertinentes à iteração em que se encontram. |
		| Procedimento de análise | Contabilizar o Número de Tarefas Concluídas na Iteração i, e dividir elas pelo Número Total de Tarefas da Iteração i. |
		| Restrições | Nenhuma |
		| Agente | Gerente de Projeto |
		| Fonte | Adaptado de Pgoraro (2014) |

		**Medidas:**

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Quantidade Total de Atividades do Projeto** |
		| Mnemônico | QTAP |
		| Definição | Mantém a contagem do número total de atividades existentes no projeto. |
		| Entidades mensuráveis | Atividades |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de atividades |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de uma iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem do número de atividades do projeto. |
		| Restrições | Nenhuma |
		| Ferramentas | Redmine | 
		| Repositório | Redmine |
		| Agentes | Gerente de Projeto |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Quantidade de Atividades Associadas ao Processo** |
		| Mnemônico | QAAP |
		| Definição | Número de atividades do projeto que tem relação com o processo. |
		| Entidades mensuráveis | Atividades |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de atividades |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de uma iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem do número de atividades do projeto que tem relação com o processo. |
		| Restrições | Nenhuma |
		| Ferramentas | Redmine |
		| Repositório | Redmine |
		| Agentes | Gerente de Projeto |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Quantidade de Atividades Definidas no Processo.** |
		| Mnemônico | QADP |
		| Definição | Número de atividades definidas no processo. |
		| Entidades mensuráveis | Atividades |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de atividades |
		| Intervalo de dados | [0,N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | No ínicio do Projeto |
		| Periodicidade | Uma vez por projeto. |
		| Procedimento de medição | Contagem do número de atividades definidas no processo. |
		| Restrições | Nenhuma |
		| Ferramentas | Camunda |
		| Repositório |  |
		| Agentes | Gerente de Projeto |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Quantidade de Atividades Não Instanciadas no Processo** |
		| Mnemônico | QANIP |
		| Definição | Número de atividades do processo que não foram associadas à uma atividade no projeto. |
		| Entidades mensuráveis | Atividades |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de atividades |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem do número de atividades do processo que não foram adequadas à uma atividade no projeto. |
		| Restrições | Nenhuma |
		| Ferramentas | Camunda, Redmine |
		| Repositório |  |
		| Agentes | Gerente de Projeto |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Número de Testes realizados na Iteração i** |
		| Mnemônico | TSTi |
		| Definição | Quantidade de testes realizados na iteração i |
		| Entidades mensuráveis | Testes |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de Testes |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem da quantidade de testes realizados em uma dada iteração i. |
		| Restrições | Nenhuma |
		| Ferramentas |  |
		| Repositório |  |
		| Agentes | Equipe de Testes |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Número de Testes Identificados com Defeito na Iteração i** |
		| Mnemônico | TST_Dfi |
		| Definição | Quantidade de testes marcados como defeituosos em uma dada iteração i.|
		| Entidades mensuráveis | Testes |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de Testes |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem de testes marcados como defeituosos em uma iteração i. |
		| Restrições | Nenhuma |
		| Ferramentas |  |
		| Repositório |  |
		| Agentes | Equipe de Testes |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Total de Classes do Projeto** |
		| Mnemônico | TCP |
		| Definição | Quantidade de classes existentes no projeto. |
		| Entidades mensuráveis | Classes |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de Classes |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem de classes existentes no projeto. |
		| Restrições | Nenhuma |
		| Ferramentas | JDL Studio |
		| Repositório | Github |
		| Agentes | Equipe de desenvolvimento |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Número de Tarefas Concluídas na iteração i** |
		| Mnemônico | NTCi |
		| Definição | Quantidade de tarefas concluídas dentro do prazo de uma dada iteração i. |
		| Entidades mensuráveis | Tarefas |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de tarefas |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem de tarefas concluídas dentro do prazo de uma dada iteração i. |
		| Restrições | Nenhuma |
		| Ferramentas | Redmine |
		| Repositório | Redmine |
		| Agentes | Gerente de Projeto |
		| Fonte |  |

		| **Atributo** | **Descrição** |
		| ---- | ---- |
		| Nome | **Número Total de Tarefas da iteração i** |
		| Mnemônico | NTTi |
		| Definição | Quantidade total de tarefas em uma dada iteração i. |
		| Entidades mensuráveis | Tarefas |
		| Elemento mensurável | Quantidade |
		| Unidade de medida | Quantidade de tarefas |
		| Intervalo de dados | [0, N] |
		| Tipo de Escala | Absoluta |
		| Valores da escala | Números inteiros |
		| Função de medição |  |
		| Momento | Ao fim de cada iteração. |
		| Periodicidade | Uma vez por iteração. |
		| Procedimento de medição | Contagem de tarefas existentes em uma dada iteração i. |
		| Restrições | Nenhuma |
		| Ferramentas | Redmine |
		| Repositório | Redmine |
		| Agentes | Gerente de Projeto |
		| Fonte |  |

3. **Recursos:**

	1. **Perfil do recurso humano:**

		Os membros do projeto devem ter a capacidade de criação e organização dos documentos e artefatos necessários ao cumprimento do processo. Além disso, os membros precisam ter conhecimento técnico em Jhipster para serem capazes de desenvolver o projeto de *software* a ser entregue até o dia 17/12.

	2. **Infra-Estrutura:**

		Equipamentos:

		Cada membro da equipe deverá ter seu próprio computador, e este deverá ter as seguintes ferramentas instaladas:

		* Github, para versionamento
		* Jhipster, para desenvolvimento do *software* em questão
		* Acesso à internet e algum *browser*, a fim de ter acesso ao Redmine para gerenciamento das tarefas do projeto.

4. **Riscos:**

	1. **Pressupostos:**

		*Pressuposto* 1: As medidas precisam ser coletadas corretamente.

		*Ação*: Seguir os procedimentos estabelecidos de coleta de cada medida.

	2. **Gestão de Riscos:**

		Se os pressupostos não forem atendidos, treinar o responsável pela medição, a fim de garantir a acurácia das medições realizadas, para que os indicadores calculados possam dar uma boa base no entendimento do processo e ajudar a melhorá-lo.

	3. **Avaliação:**

		Para poder avaliar a eficácia das medidas e seus efeitos na performance da equipe, a fim de promover a melhoria no processo, a cada nova iteração e entrega parcial será analisada a acurácia de cada medida e indicador utilizado.

5. **Referências:**

	[1] DORAN, George T. There’s a SMART way to write management’s goals and objectives. **Management review**, v. 70, n. 11, p. 35-36, 1981.

	[2] LINDVALL, Mikael; DONZELLI, Paolo; ASGARI, Sim; BASILI Vic. Towards reusable measurement patterns. In **Software Metrics**, 2005. 11th IEEE International Symposium, 21–21. IEEE, 2005.

	[3] XU, Tao. A Composite Measurement Pattern. In **Wireless Communications, Networking and Mobile Computing, 2008. WiCOM’08. 4th International Conference on**, 1–5. IEEE, 2008. 


