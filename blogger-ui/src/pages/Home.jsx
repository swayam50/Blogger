import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Home = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const res = await axios.get('/posts');
                setPosts(res.data);
            } catch (err) {
                console.error("Failed to fetch posts:", err);
            }
        };
        fetchPosts();
    }, []);

    return (
        <div className="home">
            <div className="posts">
                {posts.length === 0 ? (
                    <h2 style={{ textAlign: 'center', color: '#fff' }}>No posts available yet! Why not write one?</h2>
                ) : (
                    posts.map(post => (
                        <div className="post" key={post.id}>
                            <div className="image">
                                <img src={post.image || "https://images.pexels.com/photos/7008010/pexels-photo-7008010.jpeg"} alt="" />
                            </div>
                            <div className="content">
                                <Link className="link" to={`/post/${post.id}`}>
                                    <h1>{post.title}</h1>
                                </Link>
                                <p>{post.description}</p>
                                <Link className="link" to={`/post/${post.id}`}>
                                    <button>Read More</button>
                                </Link>
                            </div>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
};

export default Home;