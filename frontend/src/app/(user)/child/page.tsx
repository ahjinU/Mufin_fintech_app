'use client';

import useFetch from '@/hooks/useFetch';
import { useRouter } from 'next/navigation';
import { useEffect, useState } from 'react';
import { PlusButton } from '@/components';
import { ChildrenType } from './_types/types';
import { Square2StackIcon } from '@heroicons/react/24/solid';

export default function ChildInfo() {
  const [children, setChildren] = useState<ChildrenType[]>([]);
  const [showSnackbar, setShowSnackbar] = useState(false);
  const { postFetch } = useFetch();
  const router = useRouter();

  useEffect(() => {
    const fetchChildrenData = async () => {
      try {
        const res = await postFetch({ api: '/user/child' });
        console.log('hi');
        console.log(res);
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

  const handleCopyText = (text: string) => {
    navigator.clipboard.writeText(text).then(() => {
      setShowSnackbar(true);
      setTimeout(() => setShowSnackbar(false), 3000);
    });
  };

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
            {v.accountNumber && (
              <div className="flex flex-row gap-[0.2rem] items-center">
                <label className="custom-medium-text">
                  <p className="custom-medium-text text-custom-purple">
                    {v.accountNumber}
                  </p>
                </label>
                <button
                  onClick={() => handleCopyText(v.accountNumber)}
                  className="hover:bg-custom-light-gray p-1 rounded-[0.8rem]"
                >
                  <Square2StackIcon className="size-[1.3rem] fill-custom-dark-gray mb-[0.2rem]" />
                </button>
                {showSnackbar && (
                  <div
                    className="fixed bottom-[8rem] left-1/2 transform -translate-x-1/2
          p-4 rounded-[0.8rem] custom-light-text text-custom-white bg-custom-black-with-opacity z-10"
                  >
                    클립보드에 복사되었습니다!
                  </div>
                )}
              </div>
            )}
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
