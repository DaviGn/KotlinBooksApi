//@file:Suppress("UNCHECKED_CAST")
//
//package com.mercadolivro.application
//
//import com.mercadolivro.domain.interfaces.ValidationStrategy
//import com.trendyol.kediatr.PipelineBehavior
//import com.trendyol.kediatr.RequestHandlerDelegate
//import org.springframework.context.ApplicationContext
//import org.springframework.stereotype.Component
//
//inline fun <reified T> ApplicationContext.getBeansOfType(): List<T> {
//    return this.getBeansOfType(T::class.java).map { it.value }
//}
//
//@Component
//class ValidationPipelineBehavior(
//    private val context: ApplicationContext
//) : PipelineBehavior {
//    override suspend fun <TRequest, TResponse> handle(
//        request: TRequest,
//        next: RequestHandlerDelegate<TRequest, TResponse>
//    ): TResponse {
//        val validators = context.getBeansOfType<ValidationStrategy<TRequest>>()
//
//        if (validators.isEmpty())
//            return next(request)
//
//        val validationsResults = validators.map { it.validate(request ) }
//            .flatten()
//
//        if(validationsResults.isNotEmpty()){
//            return  BadRequestResponse(validationsResults) as TResponse
//        }
//
//        val response = next(request)
//        return response
//    }
//}