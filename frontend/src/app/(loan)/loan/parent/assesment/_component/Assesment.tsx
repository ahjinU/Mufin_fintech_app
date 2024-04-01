import useFetch from '@/hooks/useFetch';
import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { BackButton, Header, TinyButton } from '@/components';
import { RequestListType } from '../../_types/types';

export default function Assesment() {
  const router = useRouter();
  const [requestList, setRequestList] = useState<RequestListType[]>([]);

  const { postFetch } = useFetch();
  useEffect(() => {
    const fetchLoanData = async () => {
      try {
        const res = await postFetch({ api: '/loan/total/parents' });
        if (res?.data?.loansList) {
          setRequestList(res.data.loansList);
        }
      } catch (error) {
        console.error('아이 대출 요청 가져오기 에러', error);
      }
    };
    fetchLoanData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <Header>
        <BackButton label="아이의 대출 요청" />
      </Header>
      <div className="px-[1.2rem] pt-[0.4rem]">
        <div className="flex flex-col items-center gap-[1rem]">
          {requestList.length == 0 ? (
            <p className="py-[4rem] custom-semibold-text text-custom-black">
              대출 요청이 없습니다🤩
            </p>
          ) : (
            requestList?.map((v, k) => {
              return (
                <div
                  key={k}
                  className="w-full px-[2rem] py-[1.2rem] rounded-[2rem] bg-custom-light-gray flex justify-between items-center"
                >
                  <div>
                    <p className="custom-medium-text text-custom-black">
                      {v.reason}
                    </p>
                    <p className="custom-light-text text-custom-dark-gray">
                      {`대출 - ${v.childName}`}
                    </p>
                  </div>
                  <TinyButton
                    label="심사하기"
                    handleClick={() => {
                      router.push(`loan/parent/assesment/${k}`);
                    }}
                  />
                </div>
              );
            })
          )}
        </div>
      </div>
    </>
  );
}
