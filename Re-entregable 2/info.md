Mejoras:
Creacion del FactoryDAO
Enfasis en el singleton para en DataSource y sus conexiones
Creacion de enumerativo para el retorno de mJDBC.buscarMonedaPorNomenclatura(data, nomenclatura);

Reparacion de errores:
Solucionado:
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "clases.Stock.getMoneda()" because "stockcriptomoneda2" is null 


Extras:
Close resources no lo hicimos ya que se cierra automaticamente con el try with resources
https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
