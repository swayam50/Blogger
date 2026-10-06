import React, { useState } from 'react';
import ReactQuill from 'react-quill';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const WritePost = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('/posts', {
                title,
                description: content.replace(/<[^>]+>/g, '').substring(0, 150) + "...", // Extract text from HTML
                image: "https://images.pexels.com/photos/7008010/pexels-photo-7008010.jpeg" // Hardcoded for now
            });
            navigate('/');
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div className="write-post">
            <div className="editor">
                <input className="title" type="text" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} />
                <ReactQuill className="content" theme="snow" placeholder="Your Content Here..." value={content} onChange={setContent} />
            </div>
            <div className="menu">
                <div className="info">
                    <h1>Publish</h1>
                    <span>
                        <strong>Status: </strong> Draft
                    </span>
                    <span>
                        <strong>Visibility: </strong> Public
                    </span>
                    <input type="file" name="banner" id="banner" />
                    <label className="upload" htmlFor="banner">Upload Image</label>
                    <div className="option">
                        <button onClick={handleSubmit}>Publish</button>
                    </div>
                </div>
                <div className="info">
                    <h1>Category</h1>
                    <div className="categories">
                        <div className="category">
                            <input type="radio" name="category" id="technology" value="technology" />
                            <label htmlFor="technology">Technology</label>
                        </div>
                        <div className="category">
                            <input type="radio" name="category" id="design" value="design" />
                            <label htmlFor="design">Design</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default WritePost;