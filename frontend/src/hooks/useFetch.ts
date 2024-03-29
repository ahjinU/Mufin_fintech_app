'use client';

import { useSession } from 'next-auth/react';

type dataType = {
  data?: any;
  api: string;
};

const useFetch = () => {
  const { data: session } = useSession();
  // console.log(session);

  const UsePostFetch = async (data: dataType) => {
    // console.log(data);
    const res = await fetch(
      `${process.env.NEXT_PUBLIC_BASE_URL}/api${data.api}`,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `${session?.Authorization}`,
        },
        body: JSON.stringify(data.data),
      },
    );
    // console.log(res);
    return res.json();
  };

  const UseGetFetch = async (data: dataType) => {
    const res = await fetch(
      `${process.env.NEXT_PUBLIC_BASE_URL}/api${data.api}`,
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `${session?.Authorization}`,
        },
      },
    );
    return res.json();
  };

  return { UseGetFetch, UsePostFetch };
};

export default useFetch;
