'use client';

import { useSession } from 'next-auth/react';

type dataType = {
  data?: any;
  api: string;
};

const usePostFetch = async (data: dataType) => {
  const { data: session } = useSession();
  const res = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}${data.api}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${session?.Authorization}`,
    },
    body: JSON.stringify(data?.data),
  });
  return res.json();
};

const useGetFetch = async (data: dataType) => {
  const { data: session } = useSession();
  const res = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/${data.api}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${session?.Authorization}`,
    },
    body: JSON.stringify(data?.data),
  });
  return res.json();
};

export { usePostFetch, useGetFetch };
