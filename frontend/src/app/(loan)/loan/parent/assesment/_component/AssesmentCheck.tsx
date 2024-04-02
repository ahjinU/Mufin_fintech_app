import useFetch from '@/hooks/useFetch';
import { AlertConfirm } from '@/components';

export default function AssesmentCheck({
  uuid,
  goToComplete,
  buttonClickNo,
  goBack,
}: {
  uuid: string;
  goToComplete: () => void;
  buttonClickNo: () => void;
  goBack: () => void;
}) {
  const { postFetch } = useFetch();

  const buttonClickYes = async () => {
    try {
      const res = await postFetch({
        api: '/loan/accept',
        data: { loanUuid: uuid },
      });
      if (res.message == '대출 승인에 성공하였습니다.') {
        goToComplete();
      } else {
        console.log('대출 승인 실패');
      }
    } catch (error) {
      console.error('대출 승인 에러', error);
    }
  };

  return (
    <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
      <AlertConfirm
        isOpen
        height="h-[16rem]"
        text="아이의 대출을 승인하시겠어요?"
        content="승인 이후에는 취소가 불가능하며, 아이가 설정한 상환 기간동안 정기적으로 상환받게 됩니다."
        handleClickOkay={buttonClickYes}
        handleClickNo={buttonClickNo}
        isXButtonVisible={true}
        handleXButtonClose={goBack}
      />
    </div>
  );
}
