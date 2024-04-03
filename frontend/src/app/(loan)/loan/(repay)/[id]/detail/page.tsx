'use client';

import { Button, ComplexInput, FlexBox, GuideText } from '@/components';
import { serverPostFetch } from '@/hooks/useServerFetch';
import Image from 'next/image';
import { LoanDetailType } from '../../../_types';
import { format } from 'date-fns';
import Link from 'next/link';
import { usePathname } from 'next/navigation';
import { useEffect, useState } from 'react';
import LoanAPI from '../../../_api';
import { commaNum } from '@/utils/commaNum';

export default function LoanDetail() {
  var currentUrl = usePathname();
  var id = currentUrl?.split('/')[2];

  const { getLoanDetail } = LoanAPI();
  const [loan, setLoan] = useState<LoanDetailType>();

  useEffect(() => {
    (async function () {
      const res = id && (await getLoanDetail({ loanUuid: id }));
      setLoan(res?.data);
    })();
  }, [id]);

  return (
    <div className="flex flex-col">
      <GuideText text={`부모님께 빌렸던 돈을 갚아요.`} />
      <div className="flex items-center justify-center mb-[-3rem]">
        <Image
          src={'/images/icon-loan-repay.png'}
          width={200}
          height={200}
          alt={'대출 반환'}
          className="w-[28rem] h-[28rem]"
        />
      </div>
      <ComplexInput label={'대출 상세 내역'} mode={'NONE'}>
        <FlexBox
          isDivided={false}
          topChildren={
            <div className="my-[-0.5rem] px-[0.5rem] rounded-b-[2rem] bg-custom-light-gray h-fit flex flex-col gap-[0.5rem]">
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p className="">대출 목적</p>
                <p>{loan?.reason}</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>대출 금액</p>
                <p>{commaNum(loan?.totalAmount)} 원</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>남은 금액</p>
                <p>{commaNum(loan?.remainderAmount)} 원</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>대출 일자</p>
                <p>{loan?.startDate && format(loan.startDate, 'yyyy.MM.dd')}</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>남은 기한</p>
                <p>{loan?.remainderDay}</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>매달 상환 일자</p>
                <p>{loan?.paymentDate}일</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>연체 횟수</p>
                <p className="text-custom-red">{loan?.overDueCnt}회</p>
              </div>
            </div>
          }
        />
      </ComplexInput>{' '}
      <div className="fixed bottom-0 inset-x-0 px-[1.2rem] py-[3rem]">
        <Link href={'repay'}>
          <Button mode={'ACTIVE'} label={'다음'} />
        </Link>
      </div>
    </div>
  );
}
