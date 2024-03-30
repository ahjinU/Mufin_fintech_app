import { Button, ComplexInput, FlexBox, GuideText } from '@/components';
import { useServerPostFetch } from '@/hooks/useServerFetch';
import Image from 'next/image';
import { LoanDetailType } from '../../../_types';
import { format } from 'date-fns';
import Link from 'next/link';

export default async function LoanDetail() {
  const res = await useServerPostFetch({
    data: { loanUuid: '987bafb9-b6f6-4e9a-9ea1-93f4b6d44d3c' },
    api: '/loan/detail/child',
  });

  const loan: LoanDetailType = res?.data;

  return (
    <div className="flex flex-col gap-[1rem]">
      <GuideText text={`부모님께 빌렸던 돈을 갚아요.`} />
      <div className="flex items-center justify-center">
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
            <div className="my-[-1rem] px-[1rem] rounded-b-[2rem] bg-custom-light-gray h-fit flex flex-col gap-[0.5rem]">
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p className="">대출 목적</p>
                <p>{loan?.reason}</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>대출 금액</p>
                <p>{loan?.totalAmount}원</p>
              </div>
              <div className="flex justify-between custom-medium-text text-custom-black">
                <p>남은 금액</p>
                <p>{loan?.remainderAmount}원</p>
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
                <p className="text-custom-red">{loan?.overDueCnt}</p>
              </div>
            </div>
          }
        />
      </ComplexInput>
      <Link href={'repay'}>
        <Button mode={'ACTIVE'} label={'다음'} />
      </Link>
    </div>
  );
}
