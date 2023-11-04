import React, { useState } from 'react';
import ReactQuill from 'react-quill';

const WritePost = () => {
    const [title, setTitle] = useState(null);
    const [content, setContent] = useState(null);

    return (
        <div className="write-post">
            <div className="editor">
                <input className="title" type="text" placeholder="Title" value={title} />
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
                    <span>
                        <strong>Updated On: </strong> 07/08/2023
                    </span>
                    <input type="file" name="banner" id="banner" />
                    <label className="upload" htmlFor="banner">Upload Image</label>
                    <div className="option">
                        <button>Save</button>
                        <button>Update</button>
                    </div>
                </div>
                <div className="info">
                    <h1>Category</h1>
                    <div className="categories">
                        <div className="category">
                            <input type="radio" name="category" id="art" value="art" />
                            <label htmlFor="art">Art</label>
                        </div>
                        <div className="category">
                            <input type="radio" name="category" id="science" value="science" />
                            <label htmlFor="science">Science</label>
                        </div>
                        <div className="category">
                            <input type="radio" name="category" id="technology" value="technology" />
                            <label htmlFor="technology">Technology</label>
                        </div>
                        <div className="category">
                            <input type="radio" name="category" id="cinema" value="cinema" />
                            <label htmlFor="cinema">Cinema</label>
                        </div>
                        <div className="category">
                            <input type="radio" name="category" id="design" value="design" />
                            <label htmlFor="design">Design</label>
                        </div>
                        <div className="category">
                            <input type="radio" name="category" id="food" value="food" />
                            <label htmlFor="food">Food</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default WritePost;