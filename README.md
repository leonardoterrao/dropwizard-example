# [Dropwizard](http://www.dropwizard.io/0.9.2/docs/)

Dropwizard é um framework Java open source para o desenvolvimento de *ops-friendly*, de alto desempenho, para serviços Web RESTful. Foi desenvolvido pelo [Yammer](https://www.yammer.com/).

Dropwizard tem suporte *out-of-the-box* para a configurações sofisticadas, métricas de aplicações, logging, ferramentas operacionais, e muito mais, permitindo-nos fazer web services com qualidade no menor tempo possível.

Dropwizard reúne ótimas bibliotecas estáveis em um único pacote leve, que permite que você se concentrar em fazer as coisas. Dentre essa bibliotecas estão:
 - Embedded Jetty : cada aplicação é empacotado em um *jar* em vez de um pacote *war* e criado e inicia seu próprio *embedded jetty container*. Não há nenhum container ou servet externo. 
 - JAX-RS : Jersey (a referência de implementação para JAX-RS) usado para escrever RESTful web services. Assim, seu JAX-RS existente não é perdido.
 - JSON : os serviçoes REST se comunicam por JSON. Jackson é usado para fazer todo o processamento JSON.
 - Logging : é feito utilizando Logback e SLF4J.
 - Hibernate Validator: dropwizard usa a API do Hibernate Validator para declarar as validações.
 - Metrics : dropwizard tem suporte de monitoramento usando a bibliotecas métricas.  Ele fornece uma visão sem precedentes sobre o que o código faz em produção.

### Porque Dropwizard?

Algumas razões para aprender/utilizar Dropwizard:
 - Projeto rápido *Bootstrap*: com Spring ou Java EE, sempre temos muito trabalho para adicionar as dependências e configurar tudo para termos o ambiente pronto para densenvolver os serviços RESTful. Com Dropwizard, só temos que adicionar uma dependência em seu arquivo pom.xml, e está feito.
 - Métricas de aplicação: Dropwizard vem com suporte para métricas para a aplicação. Ele fornece informações muito úteis como o tempo de solicitação/resposta, etc. Nós só temos que colocar a anotação @Timed para obter o tempo de execução do método.
 - Produtividade: Toda aplicação Dropwizard tem um programa principal que inicia o container jetty. Isto significa que nós podemos executar e *"debugar"* o aplicativo como um programa principal de dentro do IDE. Não há necessidade de recompilar ou fazer deploy novamente do projeto.
 
### Para executar o projeto

Para executar o projeto precisamos do comando dentro da pasta do projeto:

```sh
$ java -jar target/dropwizard-example-1.0-SNAPSHOT.jar server hello-world.yml
```

Estará disponível na url: localhost:8080/hello-world?name=MyParam
