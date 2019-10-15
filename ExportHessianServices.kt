package com.waykichain.dex.base.dict

/**
 *  Created by yehuan on 2019/6/17
 */

enum class TradeDirection(var code: Int, var msg: String) {

    BUY(100,  "买"),
    SELL(200,  "卖");

    companion object {

        val map = TradeDirection.values().associateBy { it.code  }
        fun getByCode(code: Int) = map[code]
        fun getList(): Array<TradeDirection> {
            return TradeDirection.values()
        }
        val nameMap = TradeDirection.values().associateBy { it.name  }
        fun getCodeByName(name: String): Int {
            return nameMap[name]!!.code
        }
    }
}

