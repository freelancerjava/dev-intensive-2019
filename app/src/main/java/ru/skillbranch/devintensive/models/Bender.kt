package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        return if (question.answers.contains(answer)) {
            // TODO: 5/1/21
            question = question.nextQuestion()
            "Good \n${question.question}" to status.color
        } else {
            // TODO: 5/1/21
            status = status.nextStatus()
            "Wrong \n${question.question}" to status.color
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("What is my name?", listOf("bender", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("What is my profession?", listOf("programmer", "coder")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("What material I made of?", listOf("iron", "titan")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("What is my birthday?", listOf("12.12.12", "15.15.15")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("What is my serial number?", listOf("123123", "12312312312")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("That's all", listOf()) {
            override fun nextQuestion(): Question = NAME
        };

        abstract fun nextQuestion(): Question
    }
}