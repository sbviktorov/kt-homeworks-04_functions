import org.junit.Test

import org.junit.Assert.*
import kotlin.math.roundToInt

class MainKtTest {

    @Test
    fun commissionAmountLess0() {
        //arrange
        val cardType = "Mastercard"
        val amount: Long = 0
        val lastTransfer: Long = 0
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionMastercardAmountLess75k() {
        //arrange
        val cardType = "Mastercard"
        val amount: Long = 75000
        val lastTransfer: Long = 0
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionMastercardAmountOver75k() {
        //arrange
        val cardType = "Mastercard"
        val amount: Long = 75001
        val lastTransfer: Long = 0
        val expectedCommissionValue = (((lastTransfer * 100 + amount * 100) - 75_000_00) * 0.006 + 20_00).roundToInt()
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionMastercardAmountOver150k() {
        //arrange
        val cardType = "Mastercard"
        val amount: Long = 150001
        val lastTransfer: Long = 0
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionMastercardLimitNotExceed() {
        //arrange
        val cardType = "Mastercard"
        val amount: Long = 150000
        val lastTransfer: Long = 450000
        val expectedCommissionValue = (((lastTransfer * 100 + amount * 100) - 75_000_00) * 0.006 + 20_00).roundToInt()
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionMastercardLimitExceed() {
        //arrange
        val cardType = "Mastercard"
        val amount: Long = 150000
        val lastTransfer: Long = 450001
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVisaCommission35Rub() {
        //arrange
        val cardType = "Visa"
        val amount: Long = (35 / 0.0075).toLong()
        val lastTransfer: Long = 0
        val expectedCommissionValue = 35_00
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVisaCommissionOver35Rub() {
        //arrange
        val cardType = "Visa"
        val amount: Long = (35 / 0.0075).toLong() + 10
        val lastTransfer: Long = 0
        val expectedCommissionValue = (amount * 100 * 0.0075).roundToInt()
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVisaAmountHigher150k() {
        //arrange
        val cardType = "Visa"
        val amount: Long = 150001
        val lastTransfer: Long = 0
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVisaLimitNotExceed() {
        //arrange
        val cardType = "Visa"
        val amount: Long = 150000
        val lastTransfer: Long = 450000
        val expectedCommissionValue = (amount * 100 * 0.0075).roundToInt()
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVisaLimitExceed() {
        //arrange
        val cardType = "Visa"
        val amount: Long = 150000
        val lastTransfer: Long = 450001
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVKPay() {
        //arrange
        val cardType = "VK Pay"
        val amount: Long = 15000
        val lastTransfer: Long = 0
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVKPayAmountHigher15k() {
        //arrange
        val cardType = "VK Pay"
        val amount: Long = 15001
        val lastTransfer: Long = 0
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVKPayLimitNotExceed() {
        //arrange
        val cardType = "VK Pay"
        val amount: Long = 15000
        val lastTransfer: Long = 25000
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionVKPayLimitExceed() {
        //arrange
        val cardType = "VK Pay"
        val amount: Long = 15000
        val lastTransfer: Long = 25001
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionDefaultCardName() {
        //arrange
        val amount: Long = 15000
        val lastTransfer: Long = 25000
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(lastTransfer = lastTransfer, amount = amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionDefaultLastTransferCardName() {
        //arrange
        val amount: Long = 1
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(amount = amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun commissionOtherBank() {
        //arrange
        val cardType = "Другая карта"
        val amount: Long = 999999
        val lastTransfer: Long = 999999
        val expectedCommissionValue = 0
        //act
        val actualCommissionValue = ru.netology.commission(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun limitNoVKPay() {
        //arrange
        val cardType = "Maestro"
        val amount: Long = 150000
        val lastTransfer: Long = 450000
        val expectedCommissionValue = true
        //act
        val actualCommissionValue = ru.netology.limit(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun limitNoVKPayAmountOver150k() {
        //arrange
        val cardType = "Maestro"
        val amount: Long = 150001
        val lastTransfer: Long = 0
        val expectedCommissionValue = true
        //act
        val actualCommissionValue = ru.netology.limit(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun limitNoVKPayTransfersOver600k() {
        //arrange
        val cardType = "Мир"
        val amount: Long = 150000
        val lastTransfer: Long = 450001
        val expectedCommissionValue = true
        //act
        val actualCommissionValue = ru.netology.limit(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun limitVKPay() {
        //arrange
        val cardType = "VK Pay"
        val amount: Long = 15000
        val lastTransfer: Long = 25000
        val expectedCommissionValue = false
        //act
        val actualCommissionValue = ru.netology.limit(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun limitVKPayAmountOver15k() {
        //arrange
        val cardType = "VK Pay"
        val amount: Long = 15001
        val lastTransfer: Long = 0
        val expectedCommissionValue = true
        //act
        val actualCommissionValue = ru.netology.limit(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }

    @Test
    fun limitVKPayTransfersOver40k() {
        //arrange
        val cardType = "VK Pay"
        val amount: Long = 15000
        val lastTransfer: Long = 25001
        val expectedCommissionValue = true
        //act
        val actualCommissionValue = ru.netology.limit(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }


    @Test
    fun limitOtherCard() {
        //arrange
        val cardType = "Слово"
        val amount: Long = 1
        val lastTransfer: Long = 1
        val expectedCommissionValue = false
        //act
        val actualCommissionValue = ru.netology.limit(cardType, lastTransfer, amount)
        //assert
        assertEquals(expectedCommissionValue, actualCommissionValue)
    }
}