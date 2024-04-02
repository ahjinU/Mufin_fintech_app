'use client';

import useFetch from '@/hooks/useFetch';
import { useEffect, useState } from 'react';
import { Accordion, BackButton, Header } from '@/components';
import { LoanListType } from './_types/types';
import { commaNum } from '@/utils/commaNum';

export default function Page() {
  const [loanList, setLoanList] = useState<LoanListType[]>([]);
  const { postFetch } = useFetch();
  useEffect(() => {
    const fetchLoanData = async () => {
      try {
        const res = await postFetch({ api: '/loan/total/parents' });
        if (res?.data?.loansList) {
          setLoanList(res.data.loansList);
        }
      } catch (error) {
        console.error('아이 대출 정보 가져오기 에러', error);
      }
    };
    fetchLoanData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <Header>
        <BackButton label="아이 대출 현황" />
      </Header>
      <div className="px-[1.2rem] pt-[0.4rem]">
        <div className="flex flex-col gap-[1rem]">
          {loanList.length == 0 ? (
            <p className="py-[4rem] custom-semibold-text text-custom-black">
              대출 현황이 없습니다
            </p>
          ) : (
            loanList?.map((v, k) => {
              return (
                <div key={k}>
                  <Accordion
                    mode={
                      v.overdueCnt == 0
                        ? v.statusCode == '진행'
                          ? 'NORMAL'
                          : 'COMPLETED'
                        : 'EXCEPTIONAL'
                    }
                    name={v.childName}
                    title={
                      v.overdueCnt == 0
                        ? v.statusCode == '진행'
                          ? ' 아이는 꾸준히 상환 중...'
                          : ' 아이는 대출금을 모두 갚았습니다!'
                        : ` 아이는 상환이 ${v.overdueCnt}번이나 밀렸습니다!`
                    }
                    contents={[
                      ['대출 요청일', v.startDate],
                      ['상환 기한일', v.endDate],
                      ['대출 목적', v.reason],
                      ['패널티', v.penalty],
                      [
                        '남은 상환 횟수',
                        `${v.paymentTotalCnt - v.paymentNowCnt}회`,
                      ],
                      ['대출 금액', `${commaNum(v.amount)}원`],
                    ]}
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
