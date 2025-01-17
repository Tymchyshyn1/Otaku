package com.example.otaku_domain

import com.example.otaku_domain.models.Image
import com.example.otaku_domain.models.details.Genre
import com.example.otaku_domain.models.details.Studio
import com.example.otaku_domain.models.details.Video
import com.example.otaku_domain.models.details.franchise.AnimeDetailsFranchisesEntity
import com.example.otaku_domain.models.details.roles.Character
import com.example.otaku_domain.models.details.roles.Person
import com.example.otaku_domain.models.details.screenshots.AnimeDetailsScreenshotsEntity

const val SHIMORI_URL = "https://shimori-us.herokuapp.com/"
const val SHIKIMORI_URL = "https://shikimori.me/"
const val ONGOING_STATUS = "ongoing"
const val ANONS_STATUS = "anons"
const val NOT_FOUND_TEXT = "not found"

const val ERROR_WAIT_TIME = 1000L

// token values
const val USER_AGENT = "Otaku"
const val CLIENT_ID = "f5zjBHcb3RpDSxABhUPb65L3vWNQc8cI4CF_k3LXR3o"
const val CLIENT_SECRET_ID = "c7q7hBkKvEgUVJQmxtPnPyUqUXIXWrwTW543dH4aKNc"
const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"

// Genre type id
const val ROMANTIC_ID = 22
const val SHOUNEN_ID = 27
const val DRAMA_ID = 8
const val DEMONS_ID = 6
const val SHOUJO_ID = 25
const val HAREM_ID = 35

const val STATUS_FOREGROUND_ENGLISH_NAME_KEY = "ENGLISH NAME KEY"
const val STATUS_FOREGROUND_RUSSIAN_NAME_KEY = "RUSSIAN NAME KEY"
const val STATUS_FOREGROUND_KIND_KEY = "KIND KEY"

const val SHARED_PREF_SETTINGS = "SETTINGS"
const val IS_SHOW_NOTIFICATION = "NOTIFICATION"
const val IS_UK_LANGUAGE = "UKRAINE_LANGUAGE"
const val IS_TITLE_UKRAINE = "IS_TITLE_UKRAINE"
const val IS_DESCRIPTION_UKRAINE = "IS_DESCRIPTION_UKRAINE"
const val IS_NAME_UKRAINE = "IS_NAME_UKRAINE"
const val IS_DAY_NIGHT_THEME = "IS_DAY_NIGHT_THEME"
const val IS_CENSORED_SEARCH = "IS_CENSORED_SEARCH"
const val IS_SHOW_USER_AGREEMENT = "IS_SHOW_USER_AGREEMENT"
const val LOCAL_TOKEN_KEY = "LOCAL_TOKEN_KEY"
const val CURRENT_USER_ID = "CURRENT_USER_ID"

const val NO_AUTH_USER_ID = -1L
const val NO_CURRENT_USER_ID = -1L
const val NO_EPISODES_VALUE = -1L

const val DATABASE_SHIKIMORI = "shikimori_database"

const val BLUE_STATUS_COLOR = "#395DBD"
const val RED_STATUS_COLOR = "#FF5252"
const val GREEN_STATUS_COLOR = "#00C853"

const val IS_DAY_THEME = 0
const val IS_NIGHT_THEME = 1
const val IS_AUTO_THEME = 2

const val POSITION_NO_SELECTION = 0
const val POSITION_COMPLETED = 1
const val POSITION_DROPPED = 2
const val POSITION_WATCHING = 3
const val POSITION_PLANNED = 4
const val POSITION_ON_HOLD = 5
const val POSITION_DELETE = 6

const val SINGLE_LIVE_EVENT_MESSAGE =
    "Multiple observers registered but only one will be notified of changes."

// ca-app-pub-3940256099942544/1033173712
// my "ca-app-pub-9350077428310070/5938417575"
const val AD_ID_ON_BACK_PRESSED = "ca-app-pub-9350077428310070/5938417575"

val DEFAULT_CHARACTER =
    Character(
        id = 404,
        image = Image(
            original = NOT_FOUND_TEXT,
            preview = NOT_FOUND_TEXT,
            x48 = NOT_FOUND_TEXT,
            x96 = NOT_FOUND_TEXT
        ),
        name = NOT_FOUND_TEXT, russian = NOT_FOUND_TEXT, url = NOT_FOUND_TEXT
    )


val DEFAULT_PERSON =
    Person(
        id = 404,
        image = Image(
            original = NOT_FOUND_TEXT,
            preview = NOT_FOUND_TEXT,
            x48 = NOT_FOUND_TEXT,
            x96 = NOT_FOUND_TEXT
        ),
        name = NOT_FOUND_TEXT, russian = NOT_FOUND_TEXT, url = NOT_FOUND_TEXT
    )


val DEFAULT_VIDEO = Video(
    hosting = NOT_FOUND_TEXT, id = 404, image_url = "http", kind = NOT_FOUND_TEXT,
    name = NOT_FOUND_TEXT, player_url = NOT_FOUND_TEXT, url = NOT_FOUND_TEXT
)
val DEFAULT_STUDIO = Studio(
    filtered_name = NOT_FOUND_TEXT,
    id = 404,
    image = NOT_FOUND_TEXT,
    name = NOT_FOUND_TEXT,
    real = false
)
val DEFAULT_GENRE = Genre(
    id = 404,
    kind = NOT_FOUND_TEXT,
    name = NOT_FOUND_TEXT,
    russian = NOT_FOUND_TEXT
)
val DEFAULT_SCREENSHOT = AnimeDetailsScreenshotsEntity(
    original = NOT_FOUND_TEXT,
    preview = NOT_FOUND_TEXT
)
val DEFAULT_FRANCHISE =
    AnimeDetailsFranchisesEntity(
        date = 404, id = 0, image_url = "x96", kind = NOT_FOUND_TEXT, name = NOT_FOUND_TEXT,
        url = NOT_FOUND_TEXT, year = 404
    )
