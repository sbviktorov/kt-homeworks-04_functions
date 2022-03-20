package ru.netology

import kotlin.math.roundToInt

fun main() {
    commission("Mastercard", amount = 0)
    commission("Mastercard", amount = 75000)
    commission("Mastercard", amount = 75001)
    commission("Mastercard", lastTransfer = 65000, amount = 20000)
    commission("Mastercard", lastTransfer = 0, amount = 150001)
    println()
    commission("Maestro", amount = 75000)
    commission("Maestro", amount = 75001)
    commission("Maestro", lastTransfer = 75000, amount = 1)
    commission("Maestro", lastTransfer = 599999, amount = 2)
    println()
    commission("Visa", amount = 4666)
    commission("Visa", amount = 4668)
    commission("Visa", lastTransfer = 65000, amount = 20000)
    commission("Visa", lastTransfer = 0, amount = 150001)
    println()
    commission("Мир", amount = 1)
    commission("Мир", amount = 4668)
    commission("Мир", lastTransfer = 65000, amount = 20000)
    commission("Мир", lastTransfer = 600000, amount = 1)
    println()
    commission("VK Pay", amount = 15000)
    commission(amount = 15001)
    commission(lastTransfer = 39999, amount = 2)
    commission(amount = 200)
    println()
    commission("UnionPay", amount = 15000)
}

fun commission(cardType: String = "VK Pay", lastTransfer: Long = 0, amount: Long): Int {
    val sumOfTransfers = lastTransfer * 100 + amount * 100
    var commissionValue: Int = 0
    if (amount > 0) {
        when (cardType) {

            "Mastercard", "Maestro" -> {
                if (!limit(cardType, lastTransfer, amount)) {
                    if (sumOfTransfers <= 75_000_00) {
                        commissionValue = 0
                        transferMsg(cardType, amount, lastTransfer, commissionValue)
                    } else {
                        commissionValue = ((sumOfTransfers - 75_000_00) * 0.006 + 20_00).roundToInt()
                        transferMsg(cardType, amount, lastTransfer, commissionValue)
                    }
                }
            }
            "Visa", "Мир" -> {
                if (!limit(cardType, lastTransfer, amount)) {
                    if (amount * 100 * 0.0075 < 35_00) {
                        commissionValue = 35_00
                        transferMsg(cardType, amount, lastTransfer, commissionValue)
                    } else {
                        commissionValue = (amount * 100 * 0.0075).roundToInt()
                        transferMsg(cardType, amount, lastTransfer, commissionValue)
                    }

                }
            }
            "VK Pay" -> {
                if (!limit(cardType, lastTransfer, amount)) {
                    commissionValue = 0
                    transferMsg(cardType, amount, lastTransfer, commissionValue)
                }
            }
            else -> {
                commissionValue = 0
                println("Карты $cardType не обслуживаются")
            }
        }
    } else {
        commissionValue = 0
        println("Сумма операции должна быть больше 0")
    }
    return commissionValue

}

fun limit(cardType: String, lastTransfer: Long, amount: Long): Boolean {
    val currentTransferInKop = amount * 100
    val lastTransferInKop = lastTransfer * 100
    return when (cardType) {
        "Mastercard", "Maestro", "Visa", "Мир" -> {
            if ((currentTransferInKop > 150_000_00) || (currentTransferInKop + lastTransferInKop > 600_000_00)) {
                println(
                    "Перевод $amount руб. с карты $cardType невозможен. Максимальный перевод не более " +
                            "150000 руб. и не более 600000 руб. в месяц. Сумма предыдущих переводов за месяц " +
                            "$lastTransfer руб."
                )
                true
            } else {
                false
            }
        }
        "VK Pay" -> {
            if ((currentTransferInKop > 15_000_00) || (currentTransferInKop + lastTransferInKop > 40_000_00)) {
                println(
                    "Перевод $amount руб. с карты $cardType невозможен. Максимальный перевод не более " +
                            "15000 руб. и не более 40000 руб. в месяц. Сумма предыдущих переводов за месяц " +
                            "$lastTransfer руб."
                )
                true
            } else {
                false
            }

        }
        else -> {
            println("Карты $cardType не обслуживаются")
            false
        }
    }
}

fun transferMsg(cardType: String, amount: Long, lastTransfer: Long, commissionValue: Int) {
    println(
        "Комиссия за перевод $amount руб. с карты $cardType составила ${commissionValue / 100} руб. " +
                "${commissionValue % 100} коп. Ранее совершенные переводы в этом месяце $lastTransfer руб."
    )
}