package com.example.moviedbapp.utils

import com.example.moviedbapp.domain.Pelicula
import com.example.moviedbapp.framework.data.local.model.Pelicula as EntityPelicula
import com.example.moviedbapp.framework.data.remote.model.Pelicula as ResultPelicula

fun Pelicula.toRoomPelicula(): EntityPelicula =
    EntityPelicula(id, title, overview, backdrop_path, poster_path, release_date, vote_average)

fun EntityPelicula.toDomainPelicula(): Pelicula =
    Pelicula(id, title, overview, backdrop_path, poster_path, release_date, vote_average)

fun ResultPelicula.toDomainPelicula(): Pelicula =
    Pelicula(id, title, overview, backdrop_path?:poster_path?:"/nUAjSrwQldqZJVsgQs3hpQmRASS.jpg", poster_path?:"/nUAjSrwQldqZJVsgQs3hpQmRASS.jpg", release_date, vote_average)