@import es.nachobrito.jmcp.domain.service.model.ReportModel
@import gg.jte.support.ForSupport
@param ReportModel model
!{
    var methods = model.methods();
    var fields = model.fields();
}# Java `${model.classDeclaration().fullName()}` — Detailed Report

## Package and Declaration

```java
${model.classDeclaration().toString()}
```

## Fields
@for(var field:ForSupport.of(fields)) - ${field.get().toString()}
@endfor

## Methods
@for(var method:ForSupport.of(methods)) - ${method.get().toString()}@if(method.get().hasExplanation())
    Debug string:
    ```markdown
    ${method.get().explanation().replaceAll("\n", "\n    ")}
    ```
@endif
@endfor
