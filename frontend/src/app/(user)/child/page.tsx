'use client';

import useFetch from '@/hooks/useFetch';
import { useRouter } from 'next/navigation';
import { useEffect, useState } from 'react';
import { PlusButton } from '@/components';
import { ChildrenType } from './_types/types';

export default function ChildInfo() {
  const [children, setChildren] = useState<ChildrenType[]>([]);
  const { postFetch } = useFetch();
  const router = useRouter();

  useEffect(() => {
    const fetchChildrenData = async () => {
      try {
        const res = await postFetch({ api: '/user/child' });
        if (res.message == '아이 정보가 조회되었습니다.') {
          setChildren(res.data.children);
        } else {
          console.log('아이 계좌 정보 조회 실패');
        }
      } catch (error) {
        console.error('아이 계좌 정보 조회 에러', error);
      }
    };
    fetchChildrenData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="flex flex-col gap-[1rem]">
      {children.map((v, k) => {
        return (
          <div
            key={k}
            className="w-full h-[6.8rem] px-[2rem] rounded-[2rem] bg-custom-light-gray flex justify-between items-center"
          >
            <div className="flex flex-col">
              <p className="custom-semibold-text text-black">{v.name}</p>
              <p className="custom-light-text text-custom-dark-gray">
                {v.email}
              </p>
            </div>
            <p className="custom-medium-text text-custom-purple">
              {v.accountNumber}
            </p>
          </div>
        );
      })}
      <PlusButton
        label="아이 추가로 회원가입하기"
        handleClick={() => router.push('/signup')}
      />
    </div>
  );
}
