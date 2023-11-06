use bloggerDB

db.createCollection("images", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            title: "Collection of Images",
            required: ["imageId", "imageType"],
            properties: {
                imageId: {
                    bsonType: "objectId",
                    description: "Images' unique id."
                },
                imageType: {
                    bsonType: "string",
                    enum: ["USER", "POST"],
                    description: "Type of the image, either user's profile pic or post's banner."
                },
                userId: {
                    bsonType: "string",
                    description: "User's unique id."
                },
                userUsername: {
                    bsonType: "string",
                    description: "Username of the user."
                },
                postId: {
                    bsonType: "long",
                    description: "Posts' unique id."
                },
                postTitle: {
                    bsonType: "string",
                    description: "Title of the post."
                }
            }
        }
    }
});