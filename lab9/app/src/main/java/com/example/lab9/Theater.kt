package com.example.lab9

data class Theater(var name: String="", var url: String=""){
    fun suggestTheater(position:Int){
        setTheaterName(position)
        setTheaterUrl(position)
    }

    private fun setTheaterName(position:Int){
        when (position) {
            0 -> name="Century Boulder"
            1 -> name="AMC Cherry Creek"
            2 -> name="Aurora Theatre"
            else -> name="Movie theater of your choice"
        }
    }
    
    private fun setTheaterUrl(position:Int){
        when (position) {
            0-> url="https://www.cinemark.com/colorado/century-boulder"
            1 -> url="https://www.amctheatres.com/movie-theatres/denver/amc-dine-in-cherry-creek-8"
            2 -> url="https://theauroratheatre.com/"
            else -> url="https://www.google.com/search?q=movie+theater+near+me"
        }
    }
}
