import useFetch from '@/hooks/useFetch';
import { useState } from 'react';
import { AlertConfirm, Input } from '@/components';

export default function AssesmentRefusal({
  uuid,
  goToComplete,
  goBack,
}: {
  uuid: string;
  goToComplete: () => void;
  goBack: () => void;
}) {
  const [reason, setReason] = useState('');
  const { postFetch } = useFetch();

  const buttonClick = async () => {
    try {
      const res = await postFetch({
        api: '/loan/refuse',
        data: { loanUuid: uuid, reason: reason },
      });
      if (res.message == '대출 거절에 성공하였습니다.') {
        goToComplete();
      } else {
        console.log('대출 거절 실패');
      }
    } catch (error) {
      console.error('대출 거절 에러', error);
    }
  };

  return (
    <div className="fixed top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
      <AlertConfirm
        isOpen
        mode="ONLYCLOSE"
        height="h-[20rem]"
        text="거절 사유를 입력해주세요"
        content="입력한 사유는 아이가 확인할 수 있어요."
        closeButtonText="거절하기"
        handleClickClose={buttonClick}
        handleClickNo={() => {}}
        handleClickOkay={() => {}}
        isXButtonVisible={true}
        handleXButtonClose={goBack}
      >
        <div className="pb-[2rem]">
          <Input onChange={(e) => setReason(e.target.value)}></Input>
        </div>
      </AlertConfirm>
    </div>
  );
}
