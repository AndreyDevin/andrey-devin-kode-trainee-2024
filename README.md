# andrey-devin-kode-trainee-2024
�������� �� ������ � ������ ������� �� �� ����������.

1. 
������� ����� Compose+Retrofit+Koin ������
������� �������, �������� Hello World
�������� 30 ���.
���������� 20 ���.

2.
�������� API-������ (20 ���) � ��������-��������� (30 ���)
����� �������� 50 ���.
���������� 50 ���.

3.
�������� DI:
������� ����� ViewModel (30 ���)
������� Koin-������,
�������� �  Activity ������� ViewModel (30 ���)
� �������� �����, ������� �� ����� ����� API.
����� �������� 60 ���.
���������� ������� �� 50 ���, �� ��� ������� �������� ����� API, ������ �������� ������:
HTTP FAILED: java.net.SocketException: socket failed: EPERM (Operation not permitted)
��� �������, ���������� ������ �� ������� ����� � �������� (���������� � ��������� ����).
�� � �������� ��� ����� ��� ������ �� ���.
� ����� ���� ���� ������ ������� ���������� � ���������))
��������� ������ � �� ����������.
��� ��� ���������� �� ����� 3 ���� 120 ���.


4.
�������� ������ Data-����:
- getApiData
- ��������� �� ������������
- ���������� (�����) �� �����/��������
- ���������� �� ��������
- ���������� �� ��
�� 10 ��� �� �����,
���� �������������� ������ ����� - ��� �� 10 ���
����� �������� 100 ���.
���������� 270 ���.
- ������� �������� ����������� ���������� � ������������ - ��� ���������� �� ��,
- �� ����� ������������ ZonedDateTime ��� ��������,
- �� ��������� ��� ��� ��������, ����������� ���� birthday ��� ZonedDateTime.

��������� � UI. �������� ����������� MVI-������.
5.
�������� sealed class Intent, ��� ������� ��������� ��������
�������� ������������:
- ��������� ��������� "� ���������� �����������"
- ����� ������������
- ����� �� �����/��������
- ���������� �� ��������/��
- ������� ������ � ���������� �������
- �������������� ������������ (�� ������ ������������)
�������� 60 ���.
���������� 180 ��� :)
- ������� �������� ����������� ���������� � ������������ - �������� ��������, ��� ����� �������.

6.
�������� sealed class UiState, ��� ������� ��������� �������� ������ ��� �����������:
- ������ ��������������� �� ��������
- ������ ��������������� �� ��
- ���������� �������
- �� ������� �� ������ ������������ ���������������� �������� � ��������� ������
- ��� ������ (Loading)
- ��� ������ (Error)
�������� 30 ���, ����������� �� ��� � Activity + 20 ���.
���������� 200 ��� :(
- ������� �������� ����������� ���������� � ������������ - �������� ��������, ��� ����� �������,
- � � ����� ��������� ��� ���������.

6.1
�� �����, ������ ��� ���������� � ������ 7, ����� ������� UiState.
������, ��� ��� �� ������ sealed class ��������� ������, �� � �������� � ���� data class � �������,
������� ������ ������������: �����������, ����� � ��������� ������, ��� ����������.
����� �������, ��� ����� ������������ ����������, ������ ����� ���� ������������ ��������� UI.
���, ������ � ������� �� API, ���������� � ����� ������� �� �������� ������� ��������� ������.
������ 80 ���.

7.
�������� composable PersonDetails (50 ���)
���������� BackPressed (20 ���)
����� �������� 70 ���.
���������� 200 ��� :|

8.
�������� composable PersonsListItem (35 ���)
� composable PersonsLazyColumn (35 ���)
����� �������� 70 ���.
���������� 180 ���...

8.1
�� �����, ������ ��� ���������� � ������ 9, ������� ���������� BackPressed �� ������ �������,
����� ������������ �� ������ � ����������� �������� ���������.
��� ����� UiState.userChoice ������ ����� ���� val lazyListState: LazyListState
������ 50 ���.

9.
�������� � ��������� ScrollableTabRow �� ������� �������������
�������� 60 ���.
���������� 90 ���.

10.
�������� � ��������� BottomSheet � ���������� ���������� �� ��������/�� ��
�������� 50 ���.
����������:
170 ��� ��������������� �� BottomSheet
� 180 ��� �� ����������� ������, �� ���, � ���� �� ����� � ���� ���� � � ���������.
��� ����� �������� �� �����������, ������ ��� ������� �������������� ����������� ������ � UseCase
(�� ����� ��������� UI ��������������� ���������).
�� ����� �����, ��� ��� �������� �������� clean, ��� �������� ���� ����� � ��������� �������� �������.
��������� �� ������� ��� EmployeesList ���� ������� dividerIndex.

11.
�������� � ��������� SearchField (����� �� �����/��������).
����������� ��� ���� ������� "������ �� �������".
�������� 90 ���.
���������� 140 ���.

12.
����������� Pull-to-refresh
�������� 60 ���.
���������� 80 ���.

13.
����������� ������� �������� ������ (������ �� ����������)
� ���������, ��� ��������.
�������� 80 ���.
���������� 450 ��� (7� 30�) - ������� ��������� "��� ����������",
��������� ViewModel � �������� ���� :)



�����:
�������� 830 ��� (13� 50�)
���������� 2460 ��� (41� 00�)

P.S.
��� ����� ��������, ������� ���� ������������ � ���� �������, ��� � ����� ����������.
���������� ���� ��� ������ ������� MVI, �������, �� ��� ������,
������ ������� �� single activity + compose.
�� ������� � ������� ViewModel ��������� ������ ���� ����� - Intent.
� �������� ������ ������ �� ���������� ������ ������� - UiState.
���������� ����� �������������� �������������� composable-����.
�� ������� ������������ �������� � �������������� ����� ���������, �� ��� ������, ����� �� �����.

�������� ������� ��������� "������ �� ����������".
�� ������ ������ ���������� ������ ���������� - �������� � ������� ������.
�� �������� ������ ���������� ����������� ��� � ����� ����������� ���������.
��������� �� ������ �� ������ ������� ������, �� � ��������� ����������������,
�� �������� ������������ ���������.
��� ����� ���� ������ ����� �� ������ � �.�. - ����� � �������, Ok, � ��� ������?
�������� ����������� �� ��� ����������.
���������� ����� ���������:
���� � �������� ������ �� �������� ����� �������� ��������� - ������ ����������� ��� ���� �������,
�� ��� ��������� ������ ��� �����������, ��� �������, �� ������ ������...
��� 29 ������ �� ViewModel - private lateinit var savedState: UiState,
���� ��� ������� ��������� �� ��������� ���� ������ ������,
�� ��������� 3 ������� ���������� ����� ������ (� ��� ����� ������������ ��������������),
� ����� ������������ � ��������� ��������� ���������.
��� ��� � �.�. ������ ������ ���������� ������� � ����,
�� �� ������� ����� ��������� ��������� ���������� �������� ���� ��������� ������,
� ��� ���� ���� � ������ ������ ���� ������ ������ Checkbox.

�� � ������� ��������� ������� ����� ��������� � ����������� �������� ����������� �� ��������� ���������.
����������� �� ���� ��� ����� ���� - ������� ��� ����� ������� ����,
� ��������� ��������������� �������������, ��������� ���� �������� � �������.
�� ��� � � ����� ������� ��� � �������� �������� �� ������������� ;)

� �� �����, ������ � ������� ��� ��� ����������,
�������� ������, ��� ������������� ������ �� ����� ������... :)  
