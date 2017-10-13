## Como contribuir

> Texto baseado no artigo ["A successful Git branching model"](http://nvie.com/posts/a-successful-git-branching-model/) de Vincent Driessen. É uma leitura bem interessante!

### Primeiros passos

Ao clonar o repositório, por padrão, você estará na branch master do projeto. Para verificar as branchs disponíveis, digite pelo terminal:

```bash
git branch -a
```

Para mudar de branch basta utilizar:

```bash
git checkout NOME_DA_BRANCH
```

Para criar uma nova branch:

```bash
git checkout -b NOME_DA_NOVA_BRANCH
```

Note que ao criar uma branch que essa nova será filha da branch que você estava no momento da criação e não dá master, caso esteja em outra.

Uma boa prática é, antes de começar a editar os arquivos com o trabalho que você irá realizar, verifique em que branch você está digitando:

```bash
git branch
```

Também lembre que outros desenvolvedores podem ter feito mudanças nessa mesma branch (ou arquivo) enquanto você não estava utilizando. Para pegar as modificações feitas, faça sempre um pull do repositório.

```bash
git pull origin BRANCH_EM_QUESTAO
```

Então faça as mudanças que você gostaria, faça um *add*, um *commit* e *push* suas modificações. Não se esqueça de fazer uma referência a qual task está resolvendo na hora do commit, utilizando o operador *#NUM_TASK*, por exemplo:

```bash
git add artefatos/doc_artefatox.md
git commit -m"Adicionando o artefato X. Task #10"
git push origin artefatos
```

**Importante**: Evite ao máximo fazer modificações diretamente na master. Nos próximos tópicos explicaremos um workflow adequado de modificações. Em geral é uma boa prática deixar somente uma pessoa responsável por realizar o merge das branches com a branch master.

### Branches

Neste projeto teremos três branchs principais: A *master*, a *artefatos* e a *development*.

A Branch Master é a estrutura principal do projeto e tudo que nela estiver deve estar o mais estável possível (Ou seja, um caso de uso só pode ser feito merge com a master quando totalmente implementado e testado). Ao final das sprints do projeto um "release" será realizado a partir da branch master.

A Branch artefatos é onde colocaremos os principais documentos do projeto. Essa branch será unida com a master de tempos em tempos de acordo com a maturidade dos documentos, avaliação do stakeholder e final da sprint.

A Branch development é onde a codificação acontecerá. Podemos imaginar a branch "development" como um pré master, onde os testes de integração serão realizados. A implementação de casos de uso individuais devem ocorrer em branchs filhas da development, seguindo a seguinte convenção: 

* A implementação de um novo caso de uso deve seguir a seguinte nomenclatura: dev-feature-caso_de_uso (Por exemplo: dev-feature-UC001). Ao final da implementação deverão ser realizado os testes unitários e, caso tudo esteja certo, essa branch poderá ser mergeada com a development para as realizações dos testes de integração. Ao final do merge a branch será deletada.

Supondo que algum erro tenha escapado nos testes unitários de um caso de uso ou que haja algum problema na hora da integração. Uma nova branch deverá ser criada a partir da development com o intuito de corrigir esse problema.

* A correção de um caso de uso deve seguir a seguinte nomenclatura: dev-hotfix-caso_de_uso (Por exemplo: dev-hotfix-UC001). Ao final do merge a branch será deletada.

Ao final de cada sprint se ainda existirem essas branches auxiliares, elas serão deletadas e voltaremos a estrutura do *master*, *artefatos* e *development*.


*Happy Coding! (-:*