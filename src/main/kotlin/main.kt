package ru.netology

data class Comments(var count: Int)

data class Post(
    val id: Int = 0,                               // Идентификатор записи
    val text: String = "",                         // Текст записи
    val comments: Comments = Comments(0),    //количество комментариев к записи
    val date: Int = 0,                             // Время публикации записи
    val ownerId: Int = 0,                          //идентификатор владельца стены, на которой размещена запись
    val views_count: Int = 0,                      //информация о просмотрах записи (число записей)

    val attachments: Array<Attachment>? = null
)
interface Attachment {
    val type: String
}

data class PhotoAttachment(
    val photo: Photo
) : Attachment {
    override val type: String = "photo"
}
data class Photo(
    val id: Int = 0,
    val ownerId: Int = 0,
    val photo130: String = "",
    val photo604: String = ""
)
data class VideoAttachment(
    val video: Video
) : Attachment {
    override val type: String = "video"
}
data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val duration: Int = 0,
)

data class AudioAttachment(
    val audio: Audio
) : Attachment {
    override val type: String = "audio"
}
data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0,
)

data class DocAttachment(
    val doc: Doc
) : Attachment {
    override val type: String = "doc"
}
data class Doc(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val size: Int = 0
)

data class NoteAttachment(
    val note: Note
) : Attachment {
    override val type: String = "note"
}
data class Note(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val text: String = "",
    val date: Int = 0
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, comments = post.comments.copy())
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(comments = post.comments.copy())
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun print() {
        for (post in posts) {
            print(post)
            print("\n")
        }
        println()
    }
}

fun main() {
    val comments = Comments(5)
    WallService.add(Post(1, "Good Evening", comments))
    WallService.print()
    comments.count = 10
    WallService.print()

    println(WallService.update(Post(1, "Good Night", comments)))
    WallService.print()
}



