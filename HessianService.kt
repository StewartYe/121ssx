package com.waykichain.bet.webapi.config.hessian

import org.springframework.remoting.caucho.HessianExporter
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *  Created by yehuan on 2019/6/14
 */

abstract class HessianService<T, E: HessianExporter>{

    open fun uri(): String{

        return "/rpc/"+ toLowerCaseFirstChar(serviceInterface().simpleName)
    }

    open fun serviceInterface():Class<T>{
        val ifc =  getActualTypeArgument(0) as Class<T>

        if(javaClass.interfaces.filter { it.simpleName == ifc.simpleName }.firstOrNull() == null)
            throw IllegalArgumentException("the hessian service must implement the interface: ${ifc.simpleName}")

        return ifc
    } //the T is the type of HessianServiceExporter's serviceInterface


    open fun exporterClass(): Class<E>{
        return getActualTypeArgument(1) as Class<E>
    }

    private fun getActualTypeArgument(order: Int): Type{
        return  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[order]
    }

    private fun toLowerCaseFirstChar( s: String): String{
        if(Character.isLowerCase(s[0]))
            return s;
        else
            return  StringBuilder().append(Character.toLowerCase(s[0])).append(s.substring(1)).toString();
    }

}
