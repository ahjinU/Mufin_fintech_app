import AlertConfirm from '@/components/AlertConfirmModal/AlertConfirmModal';

export default function CheckModal() {
  return (
    <div>
      <AlertConfirm
        handleClickOkay={() => {}}
        handleClickNo={() => {}}
        isOpen
        text="회원가입을 진행하시겠어요?"
      />
    </div>
  );
}
