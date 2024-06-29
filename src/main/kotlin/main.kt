package ru.netology

data class Comments(var count: Int)

data class Post(
    val id: Int,
//    val authorId: Int,
    val text: String,
    val comments: Comments = Comments(0)
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
    WallService.add(Post(1, "Good Morning", comments))
    WallService.add(Post(1, "Good Evening", comments))
    WallService.print()
    comments.count = 10
    WallService.print()

    println(WallService.update(Post(1, "Good Night", comments)))
    WallService.print()
}



