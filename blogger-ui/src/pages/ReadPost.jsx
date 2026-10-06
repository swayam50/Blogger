import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link, useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Menu from '../components/Menu';

const ReadPost = () => {
    const { postId } = useParams();
    const navigate = useNavigate();
    const [post, setPost] = useState(null);

    useEffect(() => {
        const fetchPost = async () => {
            try {
                const res = await axios.get(`/posts/${postId}`);
                setPost(res.data);
            } catch (err) {
                console.error("Failed to fetch post:", err);
            }
        };
        fetchPost();
    }, [postId]);

    const handleDelete = async () => {
        try {
            await axios.delete(`/posts/${postId}`);
            navigate('/');
        } catch (err) {
            console.error("Failed to delete post:", err);
        }
    };

    if (!post) {
        return <h2 style={{ textAlign: 'center', color: '#fff', marginTop: '50px' }}>Loading...</h2>;
    }

    return (
        <div className="read-post">
            <div className="post">
                <img className="banner" src={post.image || "https://images.pexels.com/photos/1000366/pexels-photo-1000366.jpeg"} alt="post-img" />
                <div className="user">
                    <img src="https://images.pexels.com/photos/1000366/pexels-photo-1000366.jpeg" alt="user-img" />
                    <div className="info">
                        <span>User {post.userId}</span>
                        <p>Posted recently</p>
                    </div>
                    <div className="action">
                        <Link to={`/publish?edit=${post.id}`}>
                            <FontAwesomeIcon className="icon" icon="fa-solid fa-pen-to-square" style={{ color: "#00563b" }} />
                        </Link>
                        <div onClick={handleDelete}>
                            <FontAwesomeIcon className="icon" icon="fa-solid fa-trash" style={{ color: "#e81717" }} />
                        </div>
                    </div>
                </div>
                <h1 className="title">{post.title}</h1>
                <p className="content">{post.description}</p>
            </div>
            <Menu />
        </div>
    );
};

export default ReadPost;