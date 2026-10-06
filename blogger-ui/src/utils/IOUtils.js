export const convertFileToBase64String = (file, defaultBase64String) => {
    return new Promise((resolve, reject) => {
        if (!file) {
            reject(defaultBase64String);
            return;
        }

        const reader = new FileReader();
        reader.readAsDataURL(file);

        reader.onload = () => {
            resolve(reader.result);
        };

        reader.onerror = error => {
            reject(defaultBase64String);
        };
    });
};