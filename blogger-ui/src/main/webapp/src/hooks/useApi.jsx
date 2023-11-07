import { useEffect, useState } from 'react';

export default function useApi( {client, endpoint, executeApi} ) {
    const [result, modifyResult] = useState(null);
    const [error, modifyError] = useState(null);
    const [loading, modifyLoading] = useState(false);

    useEffect(() => {
        if (executeApi) {
            modifyLoading(true);
            client.request(endpoint)
                  .then(successResponse => modifyResult(successResponse.data))
                  .catch(errorResponse => modifyError(errorResponse.data))
                  .finally(() => modifyLoading(false));
        }
    }, [endpoint, executeApi]);

    return [result, error, loading];
}